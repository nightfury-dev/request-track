package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.Level;
import framgia.co.edu.ftrr.entity.ResultInterview;
import framgia.co.edu.ftrr.entity.ResultTraining;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TraineeDTO implements java.io.Serializable {
    private Integer id;
    private Level level;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User user;
    private String name;
    private String email;
    private Date startedTime;
    private String office;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private String language;
    private List<ResultTraining> resultTrainingses;
    private List<ResultInterview> resultInterviewses;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User trainer;
}
