package framgia.co.edu.ftrr.controller.div;

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

@RestController("DivInterviewController")
@RequestMapping("/div/interview")
public class InterviewController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping("/import")
    public ResponseEntity createInterviewForDiv(@RequestParam("multipartFile") MultipartFile multipartFile) {
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
