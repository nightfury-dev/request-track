package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findByEmail(String email) {
        try {
            return UserUtils.userToUserDTO(userRepository.findByEmail(email));
        } catch (Exception e) {
            logger.error("Error in findByEmail: " + e.getMessage());
            return null;
        }
    }
}
