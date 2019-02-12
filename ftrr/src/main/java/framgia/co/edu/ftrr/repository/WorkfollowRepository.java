package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Workfollow;

public interface WorkfollowRepository  extends JpaRepository<Workfollow, Integer> {
}