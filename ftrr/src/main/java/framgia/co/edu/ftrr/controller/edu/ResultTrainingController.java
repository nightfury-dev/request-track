package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("EcResultTrainingController")
@RequestMapping("/edu/resulttraining")
public class ResultTrainingController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(ResultTrainingController.class);

    @PostMapping
    public ResponseEntity<ResultTrainingDTO> commentTrainee(@RequestBody ResultTrainingDTO resultTrainingDTO) {
        try {
            ResultTrainingDTO resultTraining = getResultTrainingService().saveResultTraining(resultTrainingDTO);

            if (resultTraining == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(resultTraining, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
