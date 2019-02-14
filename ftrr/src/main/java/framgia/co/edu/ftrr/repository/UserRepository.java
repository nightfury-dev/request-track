package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}