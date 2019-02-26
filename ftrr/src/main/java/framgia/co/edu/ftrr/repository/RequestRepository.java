package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer>, RequestRepositoryCustom {

    @Query("FROM Request a WHERE a.division = :division ORDER BY a.status, a.createdAt")
    List<Request> findByDivision(@Param("division") String division);

    @Query("FROM Request a WHERE a.status IN ('Interview', 'Done') ORDER BY a.status, a.createdAt")
    List<Request> getAllByHR();

    List<Request> findAllByOrderByStatusAscCreatedAtAsc();

    @Query("select case when count(e) > 0 then true else false end from Request e where e.id = :id and e.status = :status")
    Boolean existsByIdAndStatus(@Param("id") Integer id, @Param("status") String status);
}