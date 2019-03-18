package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Division;
import framgia.co.edu.ftrr.common.FinalResultStatus;
import framgia.co.edu.ftrr.common.TraineeRequestStatus;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.response.DivStatistic;
import framgia.co.edu.ftrr.service.DashboardService;
import framgia.co.edu.ftrr.service.FinalResultService;
import framgia.co.edu.ftrr.service.RequestService;
import framgia.co.edu.ftrr.service.TraineeForRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Autowired
    RequestService requestService;
    @Autowired
    TraineeForRequestService traineeForRequestService;
    @Autowired
    FinalResultService finalResultService;

    @Override
    public Map<String, DivStatistic> getStatisticDataFollowDiv(Date fromDate) {
        try {
            Map<String, DivStatistic> mapResult = new HashMap<>();
            Division[] divisions = Division.class.getEnumConstants();
            for (Division division : divisions) {

                DivStatistic divStatistic = new DivStatistic();

                List<RequestDTO> requests = requestService.findByDivision(division.getCode());
                divStatistic.setRequestQuantity(requests.stream().map(request -> request.getId()).toArray(size -> new Integer[size]));
                divStatistic.setLanguageQuantity(requests.stream().collect(Collectors.groupingBy(RequestDTO::getLanguage, Collectors.counting())));
                divStatistic.setTraineeJoinDiv(finalResultService.loadFinalResultsByDivAndResult(division.getCode(), FinalResultStatus.JOIN.getCode()).size());
                divStatistic.setTraineeReviews(traineeForRequestService.loadTraineeForRequestsByDivisionAndStatus(division.getCode(), TraineeRequestStatus.FINISH.getValue(), fromDate).size());
                mapResult.put(division.getValue(), divStatistic);
            }
            return mapResult;
        } catch (Exception e) {
            logger.error("erroe in getStatisticDataFollowDiv:" + e.getMessage());
            return Collections.EMPTY_MAP;
        }
    }
}
