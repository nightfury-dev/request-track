package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    @Query(value = "select a from Trainee a inner join a.trainer b where b.email = :email")
    List<Trainee> findTraineesByTrainer(@Param("email") String email);
}