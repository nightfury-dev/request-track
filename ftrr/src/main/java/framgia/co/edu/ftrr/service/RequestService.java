package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
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

	public RequestDTO findById(Integer id) ;

}
