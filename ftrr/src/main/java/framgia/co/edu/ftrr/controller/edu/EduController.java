package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.service.*;
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

}
