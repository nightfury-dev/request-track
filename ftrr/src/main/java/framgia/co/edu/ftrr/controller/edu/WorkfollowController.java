package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.StepDTO;
import framgia.co.edu.ftrr.dto.request.WorkfollowDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("WorkfollowController")
@RequestMapping("/edu/workfollows")
public class WorkfollowController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(WorkfollowController.class);

    @PostMapping("/steps")
    public ResponseEntity<StepDTO> createStep(@RequestBody StepDTO step) {
        try {
            if (step.getId() != null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            StepDTO stepDTO = getStepService().saveStep(step);

            return stepDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(stepDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/steps")
    public ResponseEntity<List<StepDTO>> getListStep() {
        try {
            List<StepDTO> steps = getStepService().getListStep();

            return steps == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(steps, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkfollowDTO> getWorkfollow(@PathVariable Integer id) {
        try {
            WorkfollowDTO workfollow = getWorkfollowService().findById(id);

            return workfollow == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(workfollow, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<WorkfollowDTO>> getWorkfollows() {
        try {
            List<WorkfollowDTO> workfollows = getWorkfollowService().findAll();

            return workfollows == null || workfollows.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(workfollows, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<WorkfollowDTO> createWorkfollow(@RequestBody WorkfollowDTO workfollowDTO) {
        try {
            if (workfollowDTO.getId() != null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            WorkfollowDTO workfollow = getWorkfollowService().saveWorkfollow(workfollowDTO);

            return workfollow == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(workfollow, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<WorkfollowDTO> editWorkfollow(@PathVariable Integer id) {
        try {
            WorkfollowDTO workfollow = getWorkfollowService().findById(id);

            return workfollow == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(workfollow, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkfollowDTO> updateWorkfollow(@PathVariable Integer id, @RequestBody WorkfollowDTO workfollowDTO) {
        try {
            WorkfollowDTO workfollow = getWorkfollowService().updateWorkfollow(id, workfollowDTO);

            return workfollow == null ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
                    : new ResponseEntity<>(workfollow, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
