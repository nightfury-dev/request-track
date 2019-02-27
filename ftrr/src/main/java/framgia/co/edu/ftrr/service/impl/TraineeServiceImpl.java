package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.repository.TraineeRepository;
import framgia.co.edu.ftrr.service.TraineeService;
import framgia.co.edu.ftrr.util.TraineeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    TraineeRepository traineeRepository;

    @Override
    public List<TraineeDTO> loadTrainees() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return TraineeUtils.listTraineeToListTraineeDTO(traineeRepository.findTraineesByTrainer(email));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
