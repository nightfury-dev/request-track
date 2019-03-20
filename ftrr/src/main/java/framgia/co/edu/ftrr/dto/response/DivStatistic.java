package framgia.co.edu.ftrr.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DivStatistic {
    private Date requestTime;
    private String office;
    private String skill;
    private Integer quantity;
    private String status;
    private String result;
    private Integer interview;
    private List<String> traineesJoinDiv;
}
