package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Step;

public interface StepRepository  extends JpaRepository<Step, Integer> {
}