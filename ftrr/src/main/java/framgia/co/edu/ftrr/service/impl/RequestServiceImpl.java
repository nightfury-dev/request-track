package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.*;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.entity.*;
import framgia.co.edu.ftrr.repository.NotificationRepository;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.util.DatetimeUtils;
import framgia.co.edu.ftrr.util.InterviewUtils;
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

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class RequestServiceImpl implements RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
    @Value("notification to hr/sm/dm/ec")
    String notificationToSmDm;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
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
            Request request = requestRepository.findById(id).orElse(null);
            RequestDTO requestDTO = RequestUtils.requestToRequestDTO(request);

            List<Interview> interviews = Optional.ofNullable(request.getTraineeForRequests()).get()
                    .stream().flatMap(t -> t.getInterviews().stream()).collect(Collectors.toList());

            requestDTO.setInterviews(InterviewUtils.listInterviewToListInterviewDTO(interviews));
            return requestDTO;
        } catch (Exception e) {
            logger.error("Error in findById: " +
                    ": " + e.getMessage());
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
                                    if (traineeForRequest.getInterviews() != null) {
                                        traineeDTO.setInterviews(traineeForRequest.getInterviews());
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

    @Override
    @Transactional
    public RequestDTO confirmTraineeInterview(RequestDTO requestDTO) {
        try {
            Request request = requestRepository.findById(requestDTO.getId()).orElseThrow(() -> new EntityNotFoundException());

            Map<Integer, TraineeRequestStatus> mapTraineeForRequestStatus = requestDTO.getTraineeForRequests()
                    .stream().collect(Collectors.toMap(x -> x.getId(), x -> TraineeRequestStatus.valueOf(x.getStatus()).get()));

            request.setStatus(RequestStatus.INTERVIEW.getCode());

            for (TraineeForRequest traineeForRequest : request.getTraineeForRequests()) {
                if (mapTraineeForRequestStatus.get(traineeForRequest.getId()) == TraineeRequestStatus.PROCESSING) {
                    traineeForRequest.setStatus(TraineeRequestStatus.PROCESSING.getValue());
                } else {
                    traineeForRequest.setStatus(TraineeRequestStatus.REJECT.getValue());
                }
            }

            return RequestUtils.requestToRequestDTO(requestRepository.save(request));

        } catch (Exception e) {
            logger.error("Error in confirmTraineeInterview:" + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserDTO> loadInterviewer(Integer id) {
        try {
            Integer division = requestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()).getDivision();
            return userService.loadInterviewer(division);
        } catch (Exception e) {
            logger.error("Error in loadInterviewer: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<UserDTO> loadHRInterviewer() {
        try {
            return userService.loadInterviewer(Division.HR.getCode());
        } catch (Exception e) {
            logger.error("Error in loadHRInterviewer: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public RequestDTO updateFinalResult(Integer requestId, RequestDTO requestDTO) {
        try {
            Request request = requestRepository.findById(requestId).orElseThrow(() -> new EntityNotFoundException());
            if (request == null || !request.getStatus().equals(RequestStatus.WAITING_FINAL_RESULT.getCode()))
                return null;

            //Create a map with key is traineeForRequest id and value is its finalResult from request Dto
            Map<Integer, FinalResult> resultTrainingMap = requestDTO.getTraineeForRequests().stream()
                    .collect(Collectors.toMap(x -> x.getId(), x -> x.getFinalResult()));

            //with each TraineeForRequest set finalResult_id, planResource_id is traineeForRequest_id if they exist.
            for (Map.Entry<Integer, FinalResult> resultEntry : resultTrainingMap.entrySet()) {
                Optional.ofNullable(resultEntry.getValue()).ifPresent(finalResult -> {
                    finalResult.setId(resultEntry.getKey());
                    Optional.ofNullable(finalResult.getPlanResource()).ifPresent(planResource -> {
                        planResource.setId(resultEntry.getKey());
                    });
                });
            }

            //Change status list Trainee For Request of Request
            for (TraineeForRequest traineeForRequest : request.getTraineeForRequests()) {
                traineeForRequest.setFinalResult(resultTrainingMap.get(traineeForRequest.getId()));
                traineeForRequest.setStatus(TraineeRequestStatus.FINISH.getValue());
            }

            //Change status of request
            request.setStatus(RequestStatus.DONE.getCode());

            //Notify to EC/SM/DM/HR
            notifyFinalResult(request);

            return RequestUtils.requestToRequestDTO(requestRepository.save(request));
        } catch (Exception e) {
            logger.error("Error in updateFinalResult: " + e.getMessage());
            return null;
        }
    }

    private void notifyFinalResult(Request request) {
        List<Notification> listNotification = new ArrayList<>();
        for (User user : getListUserToNotify(request)) {
            Notification notification = new Notification();
            notification.setContent(notificationToSmDm);
            notification.setStatus(NotificationStatus.UNSEEN.getCode());
            notification.setUser(user);
            listNotification.add(notification);
        }
        notificationRepository.saveAll(listNotification);
    }

    private Set<User> getListUserToNotify(Request request) {
        Set<User> userSet = new HashSet<>();
        request.getTraineeForRequests().stream().forEach(traineeForRequest -> {
            for (Interview interview : traineeForRequest.getInterviews()) {
                userSet.add(interview.getReviewer());
            }
        });
        Integer[] rolesSmDm = {Roles.DM.getCode(), Roles.SM.getCode(), Roles.EC.getCode()};
        userRepository.findAllByDivisionAndRoleIn(request.getDivision(), rolesSmDm).stream().forEach(user -> userSet.add(user));
        return userSet;
    }
}
