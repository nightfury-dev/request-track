package framgia.co.edu.ftrr.dto.response;

import lombok.Data;

@Data
public class InterviewerSearchResponse {
    private String name;
    private String email;
    private Integer[] positionIds;
    private Integer[] workspaceIds;
}
