package framgia.co.edu.ftrr.controller.ec;

import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EcController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private NotificationService notificationService;

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public RequestService getRequestService() {
        return requestService;
    }

}
