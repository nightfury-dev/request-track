package framgia.co.edu.ftrr.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LoginMessageReponse {
    String code;
    String message;
}
