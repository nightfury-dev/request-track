package framgia.co.edu.ftrr.controller.div;

import framgia.co.edu.ftrr.service.InterviewService;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.ExcelUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public abstract class DivController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelUtils excelUtils;

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
