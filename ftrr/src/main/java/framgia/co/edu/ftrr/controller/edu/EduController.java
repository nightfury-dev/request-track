package framgia.co.edu.ftrr.controller.edu;

import framgia.co.edu.ftrr.service.*;
import framgia.co.edu.ftrr.util.ExcelUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Value("${rest.api.page-size-default}")
    private Integer pageSizeDefault;

    protected PageRequest createPageRequest(Pageable pageable) {
        if (pageable.getPageSize() != 1) {
            pageSizeDefault = pageable.getPageSize();
        }
        return PageRequest.of(pageable.getPageNumber(), pageSizeDefault, pageable.getSort());
    }

}
