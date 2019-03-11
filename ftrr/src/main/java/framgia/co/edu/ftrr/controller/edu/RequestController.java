package framgia.co.edu.ftrr.controller.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import framgia.co.edu.ftrr.dto.request.NotificationDTO;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController("EcRequestController")
@RequestMapping("/edu/requests")
@PreAuthorize("@roleServiceImpl.isEc()")
public class RequestController extends EduController {
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @GetMapping
    public ResponseEntity index(@RequestParam(value = "division", required = false) String division,
                                @RequestParam(value = "from", required = false) String from,
                                @RequestParam(value = "to", required = false) String to) {
        try {

            // role EC of Human Development Division
            return ResponseEntity.status(HttpStatus.OK).body(getRequestService().search(division, from, to));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/notify")
    public ResponseEntity<List<NotificationDTO>> notifyByRequest(@PathVariable("id") Integer id) {
        if (getRequestService().isRequestConfirmed(id)) {
            List<NotificationDTO> list = getNotificationService().sendNotification(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
