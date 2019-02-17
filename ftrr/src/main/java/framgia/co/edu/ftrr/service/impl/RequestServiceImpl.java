package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.util.RequestUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class RequestServiceImpl implements RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestRepository requestRepository;

    @Value("${error.insert.request_trainees}")
    private String insertRequestError;

    @Override
    @Transactional
    public List<RequestDTO> insertListRequest(List<RequestDTO> requests) {
        int row = 0;
        try {
            List<RequestDTO> requestDTOs = new ArrayList<>();
            for (RequestDTO request: requests) {
                requestDTOs.add(RequestUtils.requestToRequestDTO(requestRepository
                        .save(RequestUtils.requestDTOToRequest(request))));
                row++;
            }
            return requestDTOs;
        } catch (Exception e) {
            logger.error("Error in insertListRequest at row " + row + ": " + e.getMessage());
            JSONObject exception = new JSONObject();
            exception.put("exception", String.format(insertRequestError, row, e.getMessage()));
            throw new RuntimeException(exception.toJSONString(), e);
        }
    }

}
