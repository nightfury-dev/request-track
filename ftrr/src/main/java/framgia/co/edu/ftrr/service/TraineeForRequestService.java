package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.TraineeForRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface TraineeForRequestService {
    TraineeForRequestDTO saveTraineeForRequest(TraineeForRequestDTO traineeForRequest);
}
