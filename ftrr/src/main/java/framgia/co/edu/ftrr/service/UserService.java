package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {

    UserDTO findByEmail(String email);
    Boolean existsTrainer(String name, String language);
}
