package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.FinalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinalResultRepository extends JpaRepository<FinalResult, Integer> {
    @Query("From FinalResult a join a.traineeForRequest b join b.request c where a.result = :result and c.division=:div")
    List<FinalResult> loadFinalResultsByDivAndResult(@Param("div") Integer div, @Param("result") Integer result);
}