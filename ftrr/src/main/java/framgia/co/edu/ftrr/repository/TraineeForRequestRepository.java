package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.TraineeForRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TraineeForRequestRepository extends JpaRepository<TraineeForRequest, Integer> {
    @Query("select case when count(e) > 0 then true else false end from TraineeForRequest e" +
            " where e.request.id = :requestId and e.trainee.id = :traineeId")
    Boolean existsByTraineeIdAndRequestId(@Param("traineeId") Integer traineeId, @Param("requestId") Integer requestId);

    @Query("from TraineeForRequest a join a.request b where " +
            "a.status = :status and b.division= :division and b.createdAt between :fromDate and CURRENT_DATE")
    List<TraineeForRequest> loadTraineeForRequestsByDivisionAndStatus
            (@Param("division") Integer division, @Param("status") Integer status, @Param("fromDate") Date fromDate);
}