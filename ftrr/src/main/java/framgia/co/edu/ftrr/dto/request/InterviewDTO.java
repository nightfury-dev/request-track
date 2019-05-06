package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class InterviewDTO {
    private Integer id;
    private Integer resultType;
    private Integer titleInterview;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "request", "trainee", "updatedBy", "interviews", "finalResult"})
    private TraineeForRequest traineeForRequest;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User createdBy;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User updatedBy;
    private String content;
    private String result;
    private Date createdAt;
    private Date updatedAt;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User reviewer;
    private Date startTime;
    private Date endTime;
}
