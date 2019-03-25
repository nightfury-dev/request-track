package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.List;
import java.util.Map;

@RestController("EcInterviewController")
@RequestMapping("/edu/interview")
public class InterviewController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping
    public ResponseEntity createInterviewForDiv(@RequestBody Map<Integer, InterviewDTO> mapInterviewDTO) {
        try {
            List<InterviewDTO> interviews = getInterviewService().saveInterviews(mapInterviewDTO);
            return interviews == null || interviews.isEmpty() ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
                    : new ResponseEntity<>(interviews, HttpStatus.CREATED);
        } catch (DateTimeException e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
