package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.controller.edu.ResultTrainingController;
import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.entity.ResultTraining;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.repository.ResultTrainingRepository;
import framgia.co.edu.ftrr.repository.TraineeRepository;
import framgia.co.edu.ftrr.service.ResultTrainingService;
import framgia.co.edu.ftrr.util.ResultTrainingUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultTrainingServiceImpl implements ResultTrainingService {
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

    @Override
    @Transactional
    public List<ResultTrainingDTO> insertListResultTraining(List<ResultTrainingDTO> resultTrainings) {
        int row = 0;
        try {
            List<ResultTrainingDTO> resultTrainingDTOs = new ArrayList<>();

            for (ResultTrainingDTO resultTrainingDTO : resultTrainings) {
                ResultTraining resultTraining = ResultTrainingUtils.resultTrainingDTOToResultTraining(resultTrainingDTO);
                Trainee trainee = traineeRepository.save(resultTrainingDTO.getTrainee());
                resultTraining.setTrainee(trainee);

                resultTrainingDTOs.add(ResultTrainingUtils.resultTrainingToResultTrainingDTO(resultTrainingRepository
                        .save(resultTraining)));
                row++;
            }

            return resultTrainingDTOs;
            
        } catch (Exception e) {
            logger.error("Error in insertListRequest at row " + row + ": " + e.getMessage());
            JSONObject exception = new JSONObject();
            exception.put("exception", String.format("", row, e.getMessage()));
            throw new RuntimeException(exception.toJSONString(), e);
        }
    }
}
