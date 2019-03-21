package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.DatetimeUtils;
import framgia.co.edu.ftrr.util.RequestUtils;
import framgia.co.edu.ftrr.util.TraineeUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class RequestServiceImpl implements RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UserService userService;

    @Value("${error.insert.request_trainees}")
    private String insertRequestError;
    @Value("${invalid.month}")
    private String invalidMonth;

    @Override
    @Transactional
    public List<RequestDTO> insertListRequest(List<RequestDTO> requests) {
        int row = 0;
        try {
            List<RequestDTO> requestDTOs = new ArrayList<>();
            for (RequestDTO request : requests) {
                requestDTOs.add(RequestUtils
                        .requestToRequestDTO(requestRepository.save(RequestUtils.requestDTOToRequest(request))));
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

    @Override
    public List<RequestDTO> findByDivision(Integer division) {
        try {
            return RequestUtils.listRequestToListRequestDTO(requestRepository.findByDivision(division));
        } catch (Exception e) {
            logger.error("Error in findByDivision: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public RequestDTO editRequest(int id, RequestDTO requestDTO) {
        try {
            Request request = requestRepository.findById(id).get();
            if (request == null || !request.getStatus().equals(RequestStatus.WAITING.getValue()))
                return null;

            request.setQuantity(requestDTO.getQuantity());
            request.setLanguage(requestDTO.getLanguage());
            request.setDeadline(requestDTO.getDeadline());
            return RequestUtils.requestToRequestDTO(requestRepository.save(request));
        } catch (Exception e) {
            logger.error("Error in editRequest: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public RequestDTO confirmRequest(int id) {
        try {
            Request request = requestRepository.findById(id).get();
            if (request == null || !request.getStatus().equals(RequestStatus.WAITING.getValue()))
                return null;

            request.setStatus(RequestStatus.CONFIRMED.getCode());
            return RequestUtils.requestToRequestDTO(requestRepository.save(request));
        } catch (Exception e) {
            logger.error("Error in confirmRequest: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<RequestDTO> search(String division, String from, String to) {
        try {
            Integer fromYear = null;
            Integer fromMonth = null;
            Integer toYear = null;
            Integer toMonth = null;

            // month pattern: [yyyy-MM]
            if (StringUtils.isNotBlank(from)) {
                String[] temps = from.split("-");
                fromYear = Integer.parseInt(temps[0]);
                fromMonth = Integer.parseInt(temps[1]);
            }

            // month pattern: [yyyy-MM]
            if (StringUtils.isNotBlank(to)) {
                String[] temps = to.split("-");
                toYear = Integer.parseInt(temps[0]);
                toMonth = Integer.parseInt(temps[1]);
            }

            if (DatetimeUtils.isNotValidMonths(fromMonth, toMonth))
                throw new Exception(invalidMonth);

            if (fromYear == toYear && fromMonth == toMonth) {
                toYear = null;
                toMonth = null;
            }

            return RequestUtils.listRequestToListRequestDTO(requestRepository.search(division, fromYear, fromMonth, toYear, toMonth));
        } catch (Exception e) {
            logger.error("Error in search: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<RequestDTO> getAllByHR() {
        try {
            return RequestUtils.listRequestToListRequestDTO(requestRepository.getAllByHR());
        } catch (Exception e) {
            logger.error("Error in getAllByHR: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<RequestDTO> getAll() {
        try {
            return RequestUtils.listRequestToListRequestDTO(requestRepository.findAllByOrderByStatusAscCreatedAtAsc());
        } catch (Exception e) {
            logger.error("Error in getAll: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean isRequestConfirmed(Integer id) {
        return requestRepository.existsByIdAndStatus(id, RequestStatus.CONFIRMED.getValue());
    }

    @Override
    public RequestDTO findById(Integer id) {
        try {
            return RequestUtils.requestToRequestDTO(requestRepository.findById(id).orElse(null));
        } catch (Exception e) {
            logger.error("Error in getAll: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Page<RequestDTO> findRequestsWaitingConfirmPaginated(RequestStatus requestStatus, Pageable pageable) {
        try {
            Integer division = userService.loadCurrentLoginUser().getDivision();
            Page<Request> requestPage =
                    requestRepository.getRequestsByStatusAndDivision(requestStatus.getCode(), division, pageable);
            Page<RequestDTO> dtoPage = requestPage.map(new Function<Request, RequestDTO>() {
                @Override
                public RequestDTO apply(Request request) {
                    RequestDTO requestDTO = RequestUtils.requestToRequestDTO(request);
                    requestDTO.setTrainees(request.getTraineeForRequests()
                            .stream().map(traineeForRequest -> {
                                TraineeDTO traineeDTO = TraineeUtils.traineeToTraineeDTO(traineeForRequest.getTrainee());
                                if (requestStatus == RequestStatus.WAITING_FINAL_RESULT) {
                                    if (traineeForRequest.getResultInterviews() != null) {
                                        traineeDTO.setResultInterviews(traineeForRequest.getResultInterviews());
                                    }
                                }
                                return traineeDTO;
                            }).collect(Collectors.toList()));
                    return requestDTO;
                }
            });
            return dtoPage;
        } catch (Exception e) {
            logger.error("Error in findRequestsWaitingConfirmPaginated: " + e.getMessage());
            return Page.empty();
        }
    }
}
