package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.ScopeTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScopeTrainingRepository extends JpaRepository<ScopeTraining, Integer> {
    List<ScopeTraining> getAllByLanguage(String Language);
}
