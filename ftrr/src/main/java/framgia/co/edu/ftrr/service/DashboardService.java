package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.response.DivStatistic;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, List<DivStatistic>> getStatisticDataFollowDiv(Date fromDate);
}
