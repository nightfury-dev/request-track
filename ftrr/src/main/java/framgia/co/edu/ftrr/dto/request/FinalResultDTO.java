package framgia.co.edu.ftrr.dto.request;

import framgia.co.edu.ftrr.entity.PlanResource;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class FinalResultDTO {
    private int id;
    private TraineeForRequest traineeForRequest;
    private User user;
    private Integer result;
    private Date createdAt;
    private Date updatedAt;
    private PlanResource planResource;
    private float rank;
}
