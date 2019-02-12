package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.PlanResource;

public interface PlanResourceRepository  extends JpaRepository<PlanResource, Integer> {
}