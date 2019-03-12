package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.ScopeTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScopeTrainingRepository extends JpaRepository<ScopeTraining, Integer> {
    List<ScopeTraining> getAllByLanguage(String Language);

    @Query("select case when count(a) > 0 then true else false end " +
            "from ScopeTraining a inner join a.user b where a.language = :language and b.name = :name")
    Boolean existsTrainer(@Param("name") String name, @Param("language") String language);
}
