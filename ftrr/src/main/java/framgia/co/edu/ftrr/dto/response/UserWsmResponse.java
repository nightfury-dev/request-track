package framgia.co.edu.ftrr.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import framgia.co.edu.ftrr.entity.Group;
import framgia.co.edu.ftrr.entity.Position;
import framgia.co.edu.ftrr.entity.Workspace;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWsmResponse implements Serializable {
    private Integer id;
    private String email;
    private String name;
    @JsonProperty("oauth_token")
    private String oauthToken;
    private String gender;
    private String role;
    private Date birthday;
    @JsonProperty("employee_code")
    private String employeeCode;
    @JsonProperty("card_number")
    private String cardNumber;
    private String status;
    private Position position;
    @JsonProperty("staff_type")
    private String staffType;
    private List<Workspace> workspaces;
    private List<Group> groups;
}
