package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {

    List<RequestDTO> insertListRequest(List<RequestDTO> requestDTOs);

    List<RequestDTO> findByDivision(Integer division);

    RequestDTO editRequest(int id, RequestDTO requestDTO);

    RequestDTO confirmRequest(int id);

    List<RequestDTO> search(String division, String from, String to);

    List<RequestDTO> getAllByHR();

    List<RequestDTO> getAll();

    boolean isRequestConfirmed(Integer id);

    RequestDTO findById(Integer id);

    Page<RequestDTO> findRequestsWaitingConfirmPaginated(RequestStatus requestStatus, Pageable pageable);

    RequestDTO confirmTraineeInterview(RequestDTO request);

    List<UserDTO> loadInterviewer(Integer id);

    List<UserDTO> loadHRInterviewer();
}
