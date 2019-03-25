package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import org.springframework.stereotype.Service;

@Service
public interface InterviewService {
    InterviewDTO saveInterviewDTO(InterviewDTO interviewDTO, Integer traineeForRequestId);
}
