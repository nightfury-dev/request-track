package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import framgia.co.edu.ftrr.entity.Interview;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.InterviewRepository;
import framgia.co.edu.ftrr.repository.TraineeForRequestRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.InterviewService;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import framgia.co.edu.ftrr.util.InterviewUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class InterviewServiceImpl implements InterviewService {

    private static final Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TraineeForRequestRepository traineeForRequestRepository;

    @Override
    @Transactional
    public InterviewDTO saveInterviewDTO(InterviewDTO interviewDTO, Integer traineeForRequestId) {
        try {
            User reviewer = userRepository.getOneByEmail(interviewDTO.getReviewer().getEmail())
                    .orElseThrow(() -> new EntityNotFoundException());

            TraineeForRequest traineeForRequest = traineeForRequestRepository.findById(traineeForRequestId)
                    .orElseThrow(() -> new EntityNotFoundException());

            Interview interview = InterviewUtils.interviewDTOToInterview(interviewDTO);
            interview.setReviewer(reviewer);
            interview.setTraineeForRequest(traineeForRequest);
            return InterviewUtils.interviewToInterviewDTO(interviewRepository.save(interview));
        } catch (Exception e) {
            logger.error("Error in saveStep" + e.getMessage());
            throw e;
        }
    }
}
