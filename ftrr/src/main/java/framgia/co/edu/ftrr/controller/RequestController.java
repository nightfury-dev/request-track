package framgia.co.edu.ftrr.controller;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/import")
    public ResponseEntity importRequestTrainees(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                HttpServletRequest request) {
        try {
            String uploadRootPath = request.getServletContext().getRealPath("upload");
            Map<Integer, List<String>> errors = ExcelUtils.checkImportRequestTrainees(multipartFile, uploadRootPath);

            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
            }

            List<RequestDTO> requests = ExcelUtils.listRequestFromExcel(multipartFile, uploadRootPath);
            requestService.insertListRequest(requests);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
        }
    }

}
