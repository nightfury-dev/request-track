package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.entity.ResultTraining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultTrainingUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResultTrainingUtils.class);

    public static ResultTrainingDTO resultTrainingToResultTrainingDTO(ResultTraining resultTraining) {
        try {
            ResultTrainingDTO resultTrainingDTO = new ResultTrainingDTO();
            BeanUtils.copyProperties(resultTraining, resultTrainingDTO);

            return resultTrainingDTO;
        } catch (Exception e) {
            logger.error("Error in resultTrainingToResultTrainingDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<ResultTrainingDTO> listResultTrainingToListResultTrainingDTO(List<ResultTraining> resultTrainings) {
        try {
            return Optional.ofNullable(resultTrainings).orElseGet(Collections::emptyList)
                    .stream().map(resultTraining -> resultTrainingToResultTrainingDTO(resultTraining)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listResultTrainingToListResultTrainingDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static ResultTraining resultTrainingDTOToResultTraining(ResultTrainingDTO resultTrainingDTO) {
        try {
            ResultTraining resultTraining = new ResultTraining();
            BeanUtils.copyProperties(resultTrainingDTO, resultTraining);

            return resultTraining;
        } catch (Exception e) {
            logger.error("Error in resultTrainingDTOToResultTraining: " + e.getMessage());
            return null;
        }
    }

    public static List<ResultTraining> listResultTrainingDTOToListResultTraining(List<ResultTrainingDTO> resultTrainingDTOs) {
        try {
            return Optional.ofNullable(resultTrainingDTOs).orElseGet(Collections::emptyList)
                    .stream().map(resultTrainingDTO -> resultTrainingDTOToResultTraining(resultTrainingDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listResultTrainingDTOToListResultTraining: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
