package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository  extends JpaRepository<Request, Integer> {

    @Query("FROM Request a WHERE a.division = :division ORDER BY a.status, a.createdAt")
    List<Request> findByDivision(@Param("division") String division);

}