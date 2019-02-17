package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}