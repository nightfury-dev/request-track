package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.ResultType;

public interface ResultTypeRepository  extends JpaRepository<ResultType, Integer> {
}