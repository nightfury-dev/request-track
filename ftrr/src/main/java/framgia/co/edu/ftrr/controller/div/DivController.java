package framgia.co.edu.ftrr.controller.div;

import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.ExcelUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public abstract class DivController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelUtils excelUtils;

}
