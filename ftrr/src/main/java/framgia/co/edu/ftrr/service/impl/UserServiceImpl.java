package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.config.CustomPrincipal;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.ScopeTrainingRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScopeTrainingRepository scopeTrainingRepository;

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
}
