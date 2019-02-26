package framgia.co.edu.ftrr.controller.hr;

import framgia.co.edu.ftrr.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HrController {
    @Autowired
    private RequestService requestService;

    public RequestService getRequestService() {
        return requestService;
    }

}
