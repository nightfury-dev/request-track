package framgia.co.edu.ftrr.controller.div;

import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("DivTraineeForRequestController")
@RequestMapping("/edu/traineeforrequests")
public class TraineeForRequestController extends DivController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping("/{id}/interview")
    public ResponseEntity<InterviewDTO> createTraineeForRequest(@PathVariable Integer id, @RequestBody InterviewDTO interviewDTO) {
        try {
            InterviewDTO interview = getInterviewService().saveInterviewDTO(interviewDTO, id);
            return interview == null ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
                    : new ResponseEntity<>(interview, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
