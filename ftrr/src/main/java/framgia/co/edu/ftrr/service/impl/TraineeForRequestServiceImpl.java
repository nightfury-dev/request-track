package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.TraineeRequestStatus;
import framgia.co.edu.ftrr.dto.request.TraineeForRequestDTO;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.repository.TraineeForRequestRepository;
import framgia.co.edu.ftrr.repository.TraineeRepository;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import framgia.co.edu.ftrr.util.TraineeForRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeForRequestServiceImpl implements TraineeForRequestService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestServiceImpl.class);

    @Autowired
    TraineeForRequestRepository traineeForRequestRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    TraineeRepository traineeRepository;

    @Override
    @Transactional
    public TraineeForRequestDTO saveTraineeForRequest(TraineeForRequestDTO traineeForRequestDTO) {
        try {
            Integer requestId = traineeForRequestDTO.getRequest().getId();
            Integer traineeId = traineeForRequestDTO.getTrainee().getId();

            if (requestId == null || traineeId == null || !checkTraineeForRequest(requestId, traineeId)) {
                return null;
            }

            Request request = requestRepository.getOne(requestId);
            Trainee trainee = traineeRepository.getOne(traineeId);

            TraineeForRequest traineeForRequest = new TraineeForRequest();
            traineeForRequest.setTrainee(trainee);
            traineeForRequest.setRequest(request);
            traineeForRequest.setStatus(TraineeRequestStatus.WAITING_CONFIRM.getValue());
            return TraineeForRequestUtils
                    .traineeForRequestToTraineeForRequestDTO(traineeForRequestRepository.save(traineeForRequest));
        } catch (Exception e) {
            logger.error("Error in saveTraineeForRequest: "+ e.getMessage());
            throw e;
        }
    }

    // Check trainee for request have exists request, trainee and (request, trainee) in db
    private Boolean checkTraineeForRequest(Integer requestId, Integer traineeId) {
        return requestRepository.existsById(requestId) && traineeRepository.existsById(traineeId)
                && !traineeForRequestRepository.existsByTraineeIdAndRequestId(traineeId, requestId);
    }
}
