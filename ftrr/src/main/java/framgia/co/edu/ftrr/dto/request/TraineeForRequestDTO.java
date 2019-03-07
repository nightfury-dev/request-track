package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.FinalResult;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.entity.Trainee;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TraineeForRequestDTO implements Serializable {
    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "updatedBy", "createdBy", "traineeForRequests"})
    private Request request;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user", "traineeForRequests", "resultTrainings",
            "trainer", "level"})
    private Trainee trainee;
    private Integer status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @JsonIgnore
    private FinalResult finalResult;
}
