package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Trainee;

public interface TraineeRepository  extends JpaRepository<Trainee, Integer> {
}