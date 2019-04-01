package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"encryptedPassword", "resetPasswordToken",
        "resetPasswordSentAt", "rememberCreatedAt", "confirmationToken", "confirmationAt", "confirmationSentAt"})
public class UserDTO {

    private Integer id;
    private String email;
    private String code;
    private String name;
    private Integer division;
    private String phone;
    private Integer gender;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String encryptedPassword;
    private String resetPasswordToken;
    private Timestamp resetPasswordSentAt;
    private Timestamp rememberCreatedAt;
    private String confirmationToken;
    private Timestamp confirmationAt;
    private Timestamp confirmationSentAt;

}
