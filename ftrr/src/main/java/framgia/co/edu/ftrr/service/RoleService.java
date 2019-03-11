package framgia.co.edu.ftrr.service;

import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Boolean isSm();
    Boolean isDm();
    Boolean isHr();
    Boolean isEc();
    Boolean isTrainer();
    Boolean isGl();
}
