package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.entity.ResultTraining;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultTrainingService {
    ResultTrainingDTO saveResultTraining(ResultTrainingDTO resultTraining);
    List<ResultTrainingDTO> insertListResultTraining(List<ResultTrainingDTO> resultTrainings);
}
