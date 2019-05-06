package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Division;
import framgia.co.edu.ftrr.common.FinalResultStatus;
import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.response.DivStatistic;
import framgia.co.edu.ftrr.entity.FinalResult;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.service.DashboardService;
import framgia.co.edu.ftrr.service.FinalResultService;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import framgia.co.edu.ftrr.util.DatetimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Autowired
    RequestService requestService;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    TraineeForRequestService traineeForRequestService;
    @Autowired
    FinalResultService finalResultService;

    @Override
    @Transactional
    public Map<String, List<DivStatistic>> getStatisticDataFollowDiv(Date startDate) {
        try {

            Date endDate = DatetimeUtils.getCurrentTime();
            startDate = startDate == null ? DatetimeUtils.getCurrentMonth() : startDate;

            Map<String, List<DivStatistic>> mapResult = new HashMap<>();
            Division[] divisions = Division.class.getEnumConstants();
            for (Division division : divisions) {
                List<Request> requests = requestRepository.getRequestsByDivisionAndCreatedAt(division.getCode(), startDate, endDate)
                        .stream().filter(request -> checkRequestForStatistic(request)).collect(Collectors.toList());

                List<DivStatistic> divStatistics = new ArrayList<>();
                for (Request request : requests) {
                    List<FinalResult> finalResults = request.getTraineeForRequests().stream()
                            .filter(traineeForRequest -> traineeForRequest.getFinalResult() != null)
                            .map(traineeForRequest -> traineeForRequest.getFinalResult()).collect(Collectors.toList());

                    List<FinalResult> traineeJoinDivFinalResults = finalResults.stream()
                            .filter(finalResult -> finalResult.getResult() == FinalResultStatus.JOIN.getCode())
                            .collect(Collectors.toList());

                    DivStatistic divStatistic = new DivStatistic();
                    divStatistic.setRequestTime(request.getCreatedAt());
                    divStatistic.setSkill(request.getLanguage());
                    divStatistic.setQuantity(request.getQuantity());
                    divStatistic.setStatus(RequestStatus.valueOf(request.getStatus()).get().getValue());
                    divStatistic.setResult(finalResults.size() + "/" + request.getTraineeForRequests().size());
                    divStatistic.setInterview(request.getTraineeForRequests().size());
                    divStatistic.setTraineesJoinDiv(traineeJoinDivFinalResults.stream().map(finalResult ->
                            finalResult.getTraineeForRequest().getTrainee().getName()
                                    + "(" + finalResult.getPlanResource().getDate().toString() + ")"
                    ).collect(Collectors.toList()));
                    divStatistics.add(divStatistic);
                }

                mapResult.put(division.getValue(), divStatistics);

            }
            return mapResult;
        } catch (Exception e) {
            logger.error("error in getStatisticDataFollowDiv:" + e.getMessage());
            return Collections.EMPTY_MAP;
        }
    }

    private Boolean checkRequestForStatistic(Request request) {
        if (request.getStatus() == null) return false;

        RequestStatus requestStatus = RequestStatus.valueOf(request.getStatus()).orElse(null);

        return requestStatus != RequestStatus.WAITING && requestStatus != RequestStatus.CONFIRMED;
    }
}

