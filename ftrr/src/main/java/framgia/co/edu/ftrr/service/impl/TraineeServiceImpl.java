package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.controller.edu.TraineeController;
import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.entity.ResultTraining;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.repository.ResultTrainingRepository;
import framgia.co.edu.ftrr.repository.TraineeRepository;
import framgia.co.edu.ftrr.service.TraineeService;
import framgia.co.edu.ftrr.util.TraineeUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    @Autowired
    TraineeRepository traineeRepository;
    @Autowired
    ResultTrainingRepository resultTrainingRepository;

    @Override
    public List<TraineeDTO> loadTrainees() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            return TraineeUtils.listTraineeToListTraineeDTO(traineeRepository.findTraineesByTrainer(email));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public TraineeDTO findById(Integer id) {
        try {
            return TraineeUtils.traineeToTraineeDTO(traineeRepository.findById(id).orElse(null));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
