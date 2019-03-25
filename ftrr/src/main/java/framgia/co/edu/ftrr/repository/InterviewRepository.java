package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository  extends JpaRepository<Interview, Integer> {
}