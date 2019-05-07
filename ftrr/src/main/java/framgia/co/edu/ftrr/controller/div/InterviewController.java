package framgia.co.edu.ftrr.controller.div;

import framgia.co.edu.ftrr.controller.edu.TraineeForRequestController;
import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.dto.response.InterviewerSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController("DivInterviewController")
@RequestMapping("/div/interview")
public class InterviewController extends DivController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeForRequestController.class);

    @PostMapping("/import")
    public ResponseEntity createInterviewForDiv(@RequestParam("multipartFile") MultipartFile multipartFile) {
        try {
            List<InterviewDTO> interviews = getInterviewService().importFromExcel(multipartFile);

            return interviews == null || interviews.isEmpty() ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY)
                    : new ResponseEntity<>(interviews, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/interviewer",
            params = {"page", "size"})
    public ResponseEntity<Page<UserDTO>> loadInterviewer(@RequestBody InterviewerSearchResponse interviewerSearchResponse,
                                                         Pageable pageable) {
        try {
            pageable = createPageRequest(pageable);
            Page<UserDTO> requestPage = getUserService()
                    .searchInterviewers(interviewerSearchResponse, pageable);

            if (pageable.getPageNumber() > requestPage.getTotalPages()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(requestPage, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
