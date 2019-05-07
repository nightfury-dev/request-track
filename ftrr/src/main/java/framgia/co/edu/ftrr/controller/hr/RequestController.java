package framgia.co.edu.ftrr.controller.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("HrRequestController")
@RequestMapping("/hr/requests")
public class RequestController extends HrController {
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @GetMapping
    public ResponseEntity index() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(getRequestService().getAllByHR());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
