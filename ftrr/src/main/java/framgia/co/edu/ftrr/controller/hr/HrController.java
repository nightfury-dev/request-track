package framgia.co.edu.ftrr.controller.hr;

import framgia.co.edu.ftrr.service.InterviewService;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Getter
public abstract class HrController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private TraineeForRequestService traineeForRequestService;

    @Autowired
    private InterviewService interviewService;
}
