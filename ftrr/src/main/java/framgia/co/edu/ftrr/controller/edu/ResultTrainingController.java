package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
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

    @PostMapping("/import")
    public ResponseEntity importTrainees(@RequestParam("multipartFile") MultipartFile multipartFile,
                                         HttpServletRequest request) {
        try {
            String uploadRootPath = request.getServletContext().getRealPath("upload");
            JSONObject errors = getExcelUtils().checkImportTrainee(multipartFile, uploadRootPath);

            // If file import has errors
            if (errors != null && !errors.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);

            List<ResultTrainingDTO> trainingDTOs = getResultTrainingService().insertListResultTraining(
                    getExcelUtils().listResultTraingFromExcel(multipartFile, uploadRootPath));
            
            return ResponseEntity.status(HttpStatus.OK).body(trainingDTOs);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
