package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.service.*;
import framgia.co.edu.ftrr.util.ExcelUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public abstract class EduController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private TraineeForRequestService traineeForRequestService;

    @Autowired
    private ResultTrainingService resultTrainingService;

    @Autowired
    private ExcelUtils excelUtils;

    @Autowired
    private StepService stepService;

    @Autowired
    private WorkfollowService workfollowService;

    @Autowired
    private InterviewService interviewService;
}
