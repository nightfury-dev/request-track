package framgia.co.edu.ftrr.controller.hr;

import framgia.co.edu.ftrr.controller.edu.EduController;
import framgia.co.edu.ftrr.controller.edu.TraineeForRequestController;
import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("HrInterviewController")
@RequestMapping("/hr/interview")
public class InterviewController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping("/import")
    public ResponseEntity createInterviewForHr(@RequestParam("multipartFile") MultipartFile multipartFile) {
        try {
            List<InterviewDTO> interviews = getInterviewService().importFromExcel(multipartFile);
            return interviews == null || interviews.isEmpty() ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
                    : new ResponseEntity<>(interviews, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
