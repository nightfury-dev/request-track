package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.controller.edu.ResultTrainingController;
import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.entity.ResultTraining;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.repository.ResultTrainingRepository;
import framgia.co.edu.ftrr.repository.TraineeRepository;
import framgia.co.edu.ftrr.service.ResultTrainingService;
import framgia.co.edu.ftrr.util.ResultTrainingUtils;
import framgia.co.edu.ftrr.util.TraineeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultTrainingImpl implements ResultTrainingService {
    private static final Logger logger = LoggerFactory.getLogger(ResultTrainingController.class);

    @Autowired
    ResultTrainingRepository resultTrainingRepository;
    @Autowired
    TraineeRepository traineeRepository;

    @Override
    @Transactional
    public ResultTrainingDTO saveResultTraining(ResultTrainingDTO resultTrainingDTO) {
        try {
            Integer traineeId = resultTrainingDTO.getTrainee().getId();

            if (traineeId == null || !traineeRepository.existsById(traineeId)) return null;

            Trainee trainee = traineeRepository.getOne(resultTrainingDTO.getTrainee().getId());
            ResultTraining resultTraining = ResultTrainingUtils.resultTrainingDTOToResultTraining(resultTrainingDTO);
            resultTraining.setId(null);
            resultTraining.setTrainee(trainee);

            return ResultTrainingUtils.resultTrainingToResultTrainingDTO(resultTrainingRepository.save(resultTraining));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
