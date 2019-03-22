package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.Interview;
import framgia.co.edu.ftrr.entity.Level;
import framgia.co.edu.ftrr.entity.ResultTraining;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TraineeDTO implements java.io.Serializable {
    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "trainees"})
    private Level level;
    private String levelName;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User updatedBy;
    private String name;
    private String email;
    private Date startedTime;
    private String office;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private String language;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "trainee", "updatedBy", "createdBy"})
    private List<ResultTraining> resultTrainings;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User trainer;
    private String trainerName;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "trainee", "updatedBy", "createdBy"})
    private List<Interview> interviews;
}
