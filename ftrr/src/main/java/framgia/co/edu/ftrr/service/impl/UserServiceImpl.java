package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Gender;
import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.config.CustomPrincipal;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.dto.response.InterviewerSearchResponse;
import framgia.co.edu.ftrr.dto.response.UserWsmResponse;
import framgia.co.edu.ftrr.entity.Group;
import framgia.co.edu.ftrr.entity.Position;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.GroupRepository;
import framgia.co.edu.ftrr.repository.PositionRepository;
import framgia.co.edu.ftrr.repository.ScopeTrainingRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:wsm.properties", encoding = "UTF-8")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Value(value = "${check.role.ec.position.name}")
    private String ecPositionName;
    @Value("${check.role.ec.position.id}")
    private String ecPositionId;
    @Value("${check.role.ec.groups.name}")
    private String ecGroupsName;
    @Value("${check.role.sm.position.name}")
    private String smPositionName;
    @Value("${check.role.sm.position.id}")
    private Integer smPositionId;
    @Value("${check.role.dm.position.name}")
    private String dmPositionName;
    @Value("${check.role.dm.position.id}")
    private Integer dmPositionId;
    @Value("${check.role.hr.position.name}")
    private String hrPositionName;
    @Value("${check.role.hr.position.id}")
    private Integer hrPositionId;
    @Value("${check.role.trainer.position.name}")
    private String trainerPositionName;
    @Value("${check.role.trainer.position.id}")
    private Integer trainerPositionId;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScopeTrainingRepository scopeTrainingRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PositionRepository positionRepository;

    public UserServiceImpl() {
    }


    @Override
    @Transactional(readOnly = true)
    public CustomPrincipal loadCustomPrincipal(String username) {
        try {
            User user = userRepository.getOneByEmail(username).get();

            CustomPrincipal customPrincipal = new CustomPrincipal();
            customPrincipal.setUsername(username);
            customPrincipal.setPassword(user.getEncryptedPassword());
            customPrincipal.setGroups(user.getGroups());
            customPrincipal.setPosition(user.getPosition());

            return customPrincipal;
        } catch (Exception e) {
            logger.error("Error in loadCustomPrincipal: " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            return UserUtils.userToUserDTO(user);
        } catch (Exception e) {
            logger.error("Error in findByEmail: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean existsTrainer(String name, String language) {
        return scopeTrainingRepository.existsTrainer(name, language);
    }

    @Override
    public User loadCurrentLoginUser() {
        try {
            return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e) {
            logger.error("Error in findCurrentLoginUser: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public CustomPrincipal loadOrUpdateUser(UserWsmResponse userWsmResponse) {
        try {
            checkUserWsmResponse(userWsmResponse);
            User user = userRepository.getOneByEmail(userWsmResponse.getEmail()).orElse(new User());
            getValueFromWsmToUser(user, userWsmResponse);
            user = userRepository.save(user);
            return buildCustomPrincipal(user);
        } catch (Exception e) {
            logger.error("Error in loadOrUpdateUser: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<UserDTO> searchInterviewers(InterviewerSearchResponse interviewerSearchResponse, Pageable pageable) {
        try {
            Page<User> usersPage = findUserByCriteria(interviewerSearchResponse.getName(),
                    interviewerSearchResponse.getEmail(),
                    interviewerSearchResponse.getPositionIds(),
                    interviewerSearchResponse.getWorkspaceIds(),
                    pageable);

            Page<UserDTO> dtoPage = usersPage.map(new Function<User, UserDTO>() {
                @Override
                public UserDTO apply(User user) {
                    UserDTO userDTO = UserUtils.userToUserDTO(user);
                    return userDTO;
                }
            });
            return dtoPage;
        } catch (Exception e) {
            logger.error("Error in searchInterviewers: " + e.getMessage());
            return Page.empty();
        }
    }

    private Page<User> findUserByCriteria(String name,
                                          String email,
                                          Integer[] positions,
                                          Integer[] workspaces,
                                          Pageable pageable) {
        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(name)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + name + "%")));
                }
                if (StringUtils.isNotBlank(email)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("email"), "%" + email + "%")));
                }
                if (positions.length > 0) {
                    predicates.add(criteriaBuilder.and(root.get("position").get("id").in(positions)));
                }
                if (workspaces.length > 0) {
                    predicates.add(criteriaBuilder.and(root.join("workspaces").get("id").in(workspaces)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    private void checkUserWsmResponse(UserWsmResponse userWsmResponse) {
        Optional.ofNullable(userWsmResponse.getGroups()).ifPresent(groups -> {
            List<Group> groupsAfterUpdate = new ArrayList<>();
            groups.stream().forEach(group -> {
                Group g = new Group();
                g.setId(group.getId());
                g.setName(group.getName());
                if (group.getGroups() != null) {
                    g.setParentPath(JSONArray.toJSONString(group.getGroups().stream()
                            .map(Group::getId).collect(Collectors.toList())));
                }

                Example<Group> groupExample = Example.of(g);
                if (!groupRepository.exists(groupExample)) {
                    groupsAfterUpdate.add(groupRepository.save(g));
                } else {
                    groupsAfterUpdate.add(groupRepository.getOne(group.getId()));
                }
            });
            userWsmResponse.setGroups(groupsAfterUpdate);
        });
        if (isExistsPosition(userWsmResponse.getPosition())) {
            userWsmResponse.setPosition(positionRepository.getOne(userWsmResponse.getPosition().getId()));
        } else {
            userWsmResponse.setPosition(positionRepository.save(userWsmResponse.getPosition()));
        }
    }

    private boolean isExistsPosition(Position position) {
        Position positionToSearch = new Position();
        positionToSearch.setId(position.getId());
        positionToSearch.setName(position.getName());
        positionToSearch.setPositionType(position.getPositionType());

        Example<Position> positionExample = Example.of(positionToSearch);
        return positionRepository.exists(positionExample);
    }

    private void getValueFromWsmToUser(User user, UserWsmResponse userWsmResponse) {
        user.setEmail(userWsmResponse.getEmail());
        user.setCode(userWsmResponse.getEmployeeCode());
        user.setName(userWsmResponse.getName());
        user.setGender(userWsmResponse.getGender().equalsIgnoreCase(Gender.FEMALE.getValue()) ? Gender.FEMALE.getCode() : Gender.MALE.getCode());
        user.setPosition(userWsmResponse.getPosition());
        user.setGroups(userWsmResponse.getGroups());
        user.setWorkspaces(userWsmResponse.getWorkspaces());
        user.setRole(getRoleFromGroupAndPosition(userWsmResponse.getGroups(), userWsmResponse.getPosition()));
    }

    private CustomPrincipal buildCustomPrincipal(User user) {
        CustomPrincipal customPrincipal = new CustomPrincipal();
        customPrincipal.setRole(user.getRole());
        customPrincipal.setUsername(user.getEmail());
        customPrincipal.setPassword(user.getEncryptedPassword());
        customPrincipal.setPosition(user.getPosition());
        customPrincipal.setGroups(user.getGroups().stream().map(group -> {
            group.setUsers(null);
            return group;
        }).collect(Collectors.toList()));

        return customPrincipal;
    }

    private Integer getRoleFromGroupAndPosition(List<Group> groups, Position position) {
        if (position.getName().equalsIgnoreCase(dmPositionName) && position.getId().equals(dmPositionId))
            return Roles.DM.getCode();
        if (position.getName().equalsIgnoreCase(smPositionName) && position.getId().equals(smPositionId) &&
                groups.stream().anyMatch(g -> g.getParentPath() != null || g.getParentPath().equals("[]")))
            return Roles.SM.getCode();
        if (position.getName().equalsIgnoreCase(trainerPositionName) && position.getId().equals(trainerPositionId))
            return Roles.TRAINER.getCode();
        if (position.getName().equalsIgnoreCase(ecPositionName) && position.getId().equals(ecPositionId) &&
                groups.stream().anyMatch(g -> g.getName().contains(ecGroupsName)))
            return Roles.EC.getCode();
        if (position.getName().equalsIgnoreCase(hrPositionName) && position.getId().equals(hrPositionId))
            return Roles.HR.getCode();
        return Roles.OTHER.getCode();
    }
}
