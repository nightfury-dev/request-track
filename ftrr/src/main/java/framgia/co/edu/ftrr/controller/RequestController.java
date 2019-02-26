package framgia.co.edu.ftrr.controller;

import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.dto.request.NotificationDTO;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.service.impl.NotificationServiceImpl;
import framgia.co.edu.ftrr.util.ExcelUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExcelUtils excelUtils;
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping
    public ResponseEntity showRequests(Authentication authentication,
                                       @RequestParam(value = "division", required = false) String division,
                                       @RequestParam(value = "from", required = false) String from,
                                       @RequestParam(value = "to", required = false) String to) {
        UserDTO userDTO = userService.findByEmail(authentication.getName());
        boolean isManager = Roles.M.getValue().equals(userDTO.getRole()) || Roles.SM.getValue().equals(userDTO.getRole());
        boolean isEduManager = Roles.ESM.getValue().equals(userDTO.getRole());
        boolean isCoordinator = Roles.EC.getValue().equals(userDTO.getRole());
        boolean isHR = Roles.HR.getValue().equals(userDTO.getRole());

        // Is manager div
        if (isManager)
            return ResponseEntity.status(HttpStatus.OK).body(requestService.findByDivision(userDTO.getDivision().getValue()));

        // Is Education manager
        if (isEduManager)
            return ResponseEntity.status(HttpStatus.OK).body(requestService.getAll());

        // Is coordinator
        if (isCoordinator)
            return ResponseEntity.status(HttpStatus.OK).body(requestService.search(division, from, to));

        // Is HR
        if (isHR) {
            return ResponseEntity.status(HttpStatus.OK).body(requestService.getAllByHR());
        }

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

    @PutMapping("/{id}/edit")
    public ResponseEntity<RequestDTO> editRequest(@PathVariable("id") Integer id, @RequestBody RequestDTO requestDTO) {
        RequestDTO requestDTOResult = requestService.editRequest(id, requestDTO);
        if (requestDTOResult == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<RequestDTO>(requestDTOResult, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<RequestDTO> editRequest(@PathVariable("id") Integer id) {
        RequestDTO requestDTOResult = requestService.confirmRequest(id);
        if (requestDTOResult == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<RequestDTO>(requestDTOResult, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}/notify")
//    @PreAuthorize("hasRole('Education Coordinator')")
    public ResponseEntity<List<NotificationDTO>> notifyByRequest(@PathVariable("id") Integer id) {
        if (requestService.isRequestConfirmed(id)) {
            List<NotificationDTO> list = notificationService.sendNotification(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
