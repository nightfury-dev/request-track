package framgia.co.edu.ftrr.controller.div;

import framgia.co.edu.ftrr.common.Division;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController("DivisionRequestController")
@RequestMapping("/div/requests")
public class RequestController extends DivController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @GetMapping
    public ResponseEntity index(Authentication authentication) {
        try {
            UserDTO userDTO = getUserService().findByEmail(authentication.getName());

            // role SM of Human Development Division
            if (Division.HD.getValue().equals(userDTO.getDivision()))
                return ResponseEntity.status(HttpStatus.OK).body(getRequestService().getAll());

            // Role SM/DM of Division
            return ResponseEntity.status(HttpStatus.OK).body(getRequestService().findByDivision(userDTO.getDivision().getCode()));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/import")
    public ResponseEntity importRequestTrainees(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                HttpServletRequest request) {
        try {
            String uploadRootPath = request.getServletContext().getRealPath("upload");
            JSONObject errors = getExcelUtils().checkImportRequestTrainees(multipartFile, uploadRootPath);

            // If file import has errors
            if (errors != null && !errors.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);

            getRequestService().insertListRequest(getExcelUtils().listRequestFromExcel(multipartFile, uploadRootPath));
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RequestDTO> editRequest(@PathVariable("id") Integer id, @RequestBody RequestDTO requestDTO) {
        RequestDTO requestDTOResult = getRequestService().editRequest(id, requestDTO);

        if (requestDTOResult == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity(requestDTOResult, HttpStatus.OK);
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<RequestDTO> editRequest(@PathVariable("id") Integer id) {
        RequestDTO requestDTOResult = getRequestService().confirmRequest(id);

        if (requestDTOResult == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity(requestDTOResult, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable("id") Integer id) {
        RequestDTO request = getRequestService().findById(id);
        return request == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity(request, HttpStatus.OK);
    }

    @GetMapping(value = "/process",
            params = {"page", "size"})
    public ResponseEntity<Page<RequestDTO>> getRequestsWaitingConfirm(@RequestParam("page") int page, @RequestParam("size") int size) {
        try {
            Page<RequestDTO> requestPage = getRequestService().findRequestsWaitingConfirmPaginated(PageRequest.of(page, size));
            if (page > requestPage.getTotalPages()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(requestPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
