package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.entity.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TraineeUtils {
    private static final Logger logger = LoggerFactory.getLogger(TraineeUtils.class);

    public static TraineeDTO traineeToTraineeDTO(Trainee trainee) {
        try {
            TraineeDTO traineeDTO = new TraineeDTO();
            BeanUtils.copyProperties(trainee, traineeDTO);
            return traineeDTO;
        } catch (Exception e) {
            logger.error("Error in traineeToTraineeDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<TraineeDTO> listTraineeToListTraineeDTO(List<Trainee> trainees) {
        try {
            return Optional.ofNullable(trainees).orElseGet(Collections::emptyList)
                    .stream().map(trainee -> traineeToTraineeDTO(trainee)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listTraineeToListTraineeDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Trainee traineeDTOToTrainee(TraineeDTO traineeDTO) {
        try {
            Trainee trainee = new Trainee();
            BeanUtils.copyProperties(traineeDTO, trainee);
            return trainee;
        } catch (Exception e) {
            logger.error("Error in traineeDTOToTrainee: " + e.getMessage());
            return null;
        }
    }

    public static List<Trainee> listTraineeDTOToListTrainee(List<TraineeDTO> traineeDTOs) {
        try {
            return Optional.ofNullable(traineeDTOs).orElseGet(Collections::emptyList)
                    .stream().map(traineeDTO -> traineeDTOToTrainee(traineeDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listTraineeDTOToListTrainee: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
