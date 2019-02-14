package framgia.co.edu.ftrr.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDTO {

    private Integer id;
    private UserDTO createdBy;
    private UserDTO updatedBy;
    private String division;
    private Integer quantity;
    private Date deadline;
    private String language;
    private Byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @JsonIgnore
    private List<TraineeForRequest> traineeForRequests;

}
