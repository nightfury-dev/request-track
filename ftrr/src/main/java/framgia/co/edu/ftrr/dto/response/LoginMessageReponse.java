package framgia.co.edu.ftrr.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framgia.co.edu.ftrr.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginMessageReponse {
    String code;
    String message;
}
