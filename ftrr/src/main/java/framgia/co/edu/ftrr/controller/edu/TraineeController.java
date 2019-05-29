package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("EcTraineeController")
@RequestMapping("/edu/trainees")
public class TraineeController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @GetMapping
//    @PreAuthorize("@roleServiceImpl.isTrainer()")
    public ResponseEntity<List<TraineeDTO>> index() {
        try {
            return new ResponseEntity<>(getTraineeService().loadTrainees(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable Integer id) {
        try {
            TraineeDTO trainee = getTraineeService().findById(id);

            if (trainee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(trainee, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
