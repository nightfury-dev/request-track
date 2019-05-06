package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class NotificationDTO {
    private Integer id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User user;
    private String content;
    private String status;
    private Integer urlId;
    private Date createdAt;
    private Date updatedAt;
    private Date readedAt;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "encryptedPassword", "resetPasswordToken",
            "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
    private User userRequest;
    private Date deletedAt;
}
