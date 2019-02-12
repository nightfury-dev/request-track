package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Level;

public interface LevelRepository  extends JpaRepository<Level, Integer> {
}