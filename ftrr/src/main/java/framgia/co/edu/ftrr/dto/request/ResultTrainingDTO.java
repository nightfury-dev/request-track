package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.Trainee;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ResultTrainingDTO implements Serializable {
    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user", "traineeForRequests", "resultTrainings",
            "trainer", "level"})
    private Trainee trainee;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User updatedBy;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User createdBy;
    private Integer point;
    private String content;
    private String result;
    private Date createdAt;
}
