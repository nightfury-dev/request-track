package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.TraineeForRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("EcTraineeForRequestController")
@RequestMapping("/edu/traineeforrequests")
public class TraineeForRequestController extends EcController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping
    @PreAuthorize("hasRole('EC')")
    public ResponseEntity<TraineeForRequestDTO> createTraineeForRequest(@RequestBody TraineeForRequestDTO traineeForRequest) {
        try {
            TraineeForRequestDTO traineeForRequestDTO = getTraineeForRequestService().saveTraineeForRequest(traineeForRequest);
            return traineeForRequestDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(traineeForRequestDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
