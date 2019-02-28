package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.TraineeForRequestDTO;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TraineeForRequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestUtils.class);

    public static TraineeForRequestDTO traineeForRequestToTraineeForRequestDTO(TraineeForRequest traineeForRequest) {
        try {
            TraineeForRequestDTO traineeForRequestDTO = new TraineeForRequestDTO();
            BeanUtils.copyProperties(traineeForRequest, traineeForRequestDTO);
            return traineeForRequestDTO;
        } catch (Exception e) {
            logger.error("Error in traineeForRequestToTraineeForRequestDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<TraineeForRequestDTO> listTraineeForRequestToListTraineeForRequestDTO(List<TraineeForRequest> traineeForRequests) {
        try {
            return Optional.ofNullable(traineeForRequests).orElseGet(Collections::emptyList)
                    .stream().map(traineeForRequest -> traineeForRequestToTraineeForRequestDTO(traineeForRequest)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listTraineeForRequestToListTraineeForRequestDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static TraineeForRequest traineeForRequestDTOToTraineeForRequest(TraineeForRequestDTO traineeForRequestDTO) {
        try {
            TraineeForRequest traineeForRequest = new TraineeForRequest();
            BeanUtils.copyProperties(traineeForRequestDTO, traineeForRequest);
            return traineeForRequest;
        } catch (Exception e) {
            logger.error("Error in traineeForRequestDTOToTraineeForRequest: " + e.getMessage());
            return null;
        }
    }

    public static List<TraineeForRequest> listTraineeForRequestDTOToListTraineeForRequest(List<TraineeForRequestDTO> traineeForRequestDTOs) {
        try {
            return Optional.ofNullable(traineeForRequestDTOs).orElseGet(Collections::emptyList)
                    .stream().map(traineeForRequestDTO -> traineeForRequestDTOToTraineeForRequest(traineeForRequestDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listTraineeForRequestDTOToListTraineeForRequest: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
