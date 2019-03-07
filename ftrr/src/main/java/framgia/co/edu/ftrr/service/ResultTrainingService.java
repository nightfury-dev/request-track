package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResultTrainingService {
    ResultTrainingDTO saveResultTraining(ResultTrainingDTO resultTraining);
}
