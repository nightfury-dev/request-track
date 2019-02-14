package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.entity.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public static RequestDTO requestToRequestDTO(Request request) {
        try {
            RequestDTO requestDTO = new RequestDTO();
            BeanUtils.copyProperties(request, requestDTO);
            return requestDTO;
        } catch (Exception e) {
            logger.error("Error in requestToRequestDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<RequestDTO> listRequestToListRequestDTO(List<Request> requests) {
        try {
            return requests.stream().map(request -> requestToRequestDTO(request)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listRequestToListRequestDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Request requestDTOToRequest(RequestDTO requestDTO) {
        try {
            Request request = new Request();
            BeanUtils.copyProperties(requestDTO, request);
            return request;
        } catch (Exception e) {
            logger.error("Error in requestDTOToRequest: " + e.getMessage());
            return null;
        }
    }

    public static List<Request> listRequestDTOToListRequest(List<RequestDTO> requestDTOs) {
        try {
            return requestDTOs.stream().map(requestDTO -> requestDTOToRequest(requestDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listRequestDTOToListRequest: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
