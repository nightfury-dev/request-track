package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RequestDTO implements java.io.Serializable {

    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User createdBy;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User updatedBy;
    private Integer division;
    private Integer quantity;
    private Date deadline;
    private String language;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private List<TraineeDTO> trainees;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "updatedBy", "request", "resultInterviews", "trainee", "interviews"})
    private List<TraineeForRequest> traineeForRequests;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "traineeForRequest", "createdBy", "updatedBy", "interviews"})
    private List<InterviewDTO> interviews;
}
