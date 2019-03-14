package framgia.co.edu.ftrr.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WorkfollowDTO {
    private Integer id;
    private String name;
    private String description;
    private Byte status;
    private String steps;
    private List<StepDTO> listStep;
    private Date deletedAt;
    private Date createdAt;
    private Date updatedAt;
}
