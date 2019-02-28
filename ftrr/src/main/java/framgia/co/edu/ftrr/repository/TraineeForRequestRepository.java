package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeForRequestRepository extends JpaRepository<TraineeForRequest, Integer> {
    @Query("select case when count(e) > 0 then true else false end from TraineeForRequest e" +
            " where e.request.id = :requestId and e.trainee.id = :traineeId")
    Boolean existsByTraineeIdAndRequestId(@Param("traineeId") Integer traineeId,@Param("requestId") Integer requestId);
}