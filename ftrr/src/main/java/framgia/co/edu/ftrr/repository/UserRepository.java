package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import framgia.co.edu.ftrr.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	User findOneByEmail (String email);
}