package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.service.NotificationService;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import framgia.co.edu.ftrr.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EcController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private TraineeForRequestService traineeForRequestService;

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public RequestService getRequestService() {
        return requestService;
    }

    public TraineeService getTraineeService() {
        return traineeService;
    }

    public TraineeForRequestService getTraineeForRequestService() {
        return traineeForRequestService;
    }
}
