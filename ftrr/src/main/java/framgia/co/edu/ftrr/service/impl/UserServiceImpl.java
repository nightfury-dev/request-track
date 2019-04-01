package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Gender;
import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.config.CustomPrincipal;
import framgia.co.edu.ftrr.dto.request.UserDTO;
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
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScopeTrainingRepository scopeTrainingRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PositionRepository positionRepository;

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
    public List<UserDTO> loadInterviewer(Integer division) {
        try {
            Integer[] interviewRoleArray = new Integer[]{
                    Roles.GL.getCode(),
                    Roles.TL.getCode()
            };

            return UserUtils.listUserToListUserDTO(userRepository.findAllByDivisionAndRoleIn(division, interviewRoleArray));
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
    }

    private CustomPrincipal buildCustomPrincipal(User user) {
        CustomPrincipal customPrincipal = new CustomPrincipal();

        customPrincipal.setUsername(user.getEmail());
        customPrincipal.setPassword(user.getEncryptedPassword());
        customPrincipal.setPosition(user.getPosition());
        customPrincipal.setGroups(user.getGroups().stream().map(group -> {
            group.setUsers(null);
            return group;
        }).collect(Collectors.toList()));

        return customPrincipal;
    }
}
