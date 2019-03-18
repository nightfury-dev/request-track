package framgia.co.edu.ftrr.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class DivStatistic {
    private Integer[] requestQuantity;
    private Map<String, Long> languageQuantity;
    private Integer traineeReviews;
    private Integer traineeJoinDiv;
}
