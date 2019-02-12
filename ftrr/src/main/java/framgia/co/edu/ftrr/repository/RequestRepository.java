package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Request;

public interface RequestRepository  extends JpaRepository<Request, Integer> {
}