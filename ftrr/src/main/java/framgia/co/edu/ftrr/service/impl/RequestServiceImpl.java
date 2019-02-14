package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestRepository requestRepository;

    @Override
    @Transactional
    public List<RequestDTO> insertListRequest(List<RequestDTO> requests) {
        try {
            List<RequestDTO> requestDTOs = new ArrayList<>();
            requests.stream().forEach(request -> {
                requestDTOs.add(RequestUtils.requestToRequestDTO(requestRepository
                        .save(RequestUtils.requestDTOToRequest(request))));
            });
            return requestDTOs;
        } catch (Exception e) {
            logger.error("Error in insertListRequest: " + e.getMessage());
            throw e;
        }
    }
}
