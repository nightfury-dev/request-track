package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.entity.User;

import java.util.List;


public interface UserService {

    UserDTO findByEmail(String email);

    Boolean existsTrainer(String name, String language);

    User loadCurrentLoginUser();

    List<UserDTO> loadInterviewer(Integer division);
}
