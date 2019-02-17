package framgia.co.edu.ftrr.controller;

import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.ExcelUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExcelUtils excelUtils;

    @GetMapping
    public ResponseEntity showRequests(Authentication authentication) {
        UserDTO userDTO = userService.findByEmail(authentication.getName());
        boolean isManager = userDTO.getRole().equals(Roles.M.getValue()) || userDTO.getRole().equals(Roles.SM.getValue());

        // Is manager div
        if (isManager)
            return ResponseEntity.status(HttpStatus.OK).body(requestService.findByDivision(userDTO.getDivision().getValue()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/import")
    public ResponseEntity importRequestTrainees(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                HttpServletRequest request) {
        try {
            String uploadRootPath = request.getServletContext().getRealPath("upload");
            JSONObject errors = excelUtils.checkImportRequestTrainees(multipartFile, uploadRootPath);

            // If file import has errors
            if (errors != null && !errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
            }

            requestService.insertListRequest(excelUtils.listRequestFromExcel(multipartFile, uploadRootPath));
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
