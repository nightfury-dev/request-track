package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Step;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StepRepository  extends JpaRepository<Step, Integer> {
    @Query("From Step a where a.id in (:ids)")
    List<Step> getListByIds(@Param("ids") List<Integer> ids);
}