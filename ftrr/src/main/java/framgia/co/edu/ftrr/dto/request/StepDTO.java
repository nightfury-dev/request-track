package framgia.co.edu.ftrr.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StepDTO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Date deletedAt;
    private Date createdAt;
    private Date updateAt;
}
