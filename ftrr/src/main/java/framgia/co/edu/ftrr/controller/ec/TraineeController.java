package framgia.co.edu.ftrr.controller.ec;

import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("EcTraineeController")
@RequestMapping("/edu/trainees")

public class TraineeController extends EcController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @GetMapping
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<TraineeDTO>> index() {
        try {
            return new ResponseEntity<>(getTraineeService().loadTrainees(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
