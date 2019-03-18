package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.TraineeForRequestDTO;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TraineeForRequestService {
    TraineeForRequestDTO saveTraineeForRequest(TraineeForRequestDTO traineeForRequest);
    List<TraineeForRequest> loadTraineeForRequestsByDivisionAndStatus(Integer division, Integer status, Date from);
}
