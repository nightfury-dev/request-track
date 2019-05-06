package framgia.co.edu.ftrr.util;


import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import framgia.co.edu.ftrr.entity.Interview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewUtils {

    private static final Logger logger = LoggerFactory.getLogger(InterviewUtils.class);

    public static InterviewDTO interviewToInterviewDTO(Interview interview) {
        try {
            InterviewDTO interviewDTO = new InterviewDTO();
            BeanUtils.copyProperties(interview, interviewDTO);

            return interviewDTO;
        } catch (Exception e) {
            logger.error("Error in interviewToInterviewDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<InterviewDTO> listInterviewToListInterviewDTO(List<Interview> interviews) {
        try {
            return interviews == null ? Collections.emptyList() :
                    interviews.stream().map(interview -> interviewToInterviewDTO(interview)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listInterviewToListInterviewDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Interview interviewDTOToInterview(InterviewDTO interviewDTO) {
        try {
            Interview interview = new Interview();
            BeanUtils.copyProperties(interviewDTO, interview);

            return interview;
        } catch (Exception e) {
            logger.error("Error in interviewDTOToInterview: " + e.getMessage());
            return null;
        }
    }

    public static List<Interview> listInterviewDTOToListInterview(List<InterviewDTO> interviewDTOs) {
        try {

            return interviewDTOs == null ? Collections.emptyList() :
                    interviewDTOs.stream().map(interviewDTO -> interviewDTOToInterview(interviewDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listInterviewDTOToListInterview: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
