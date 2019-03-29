package framgia.co.edu.ftrr.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class UserWsmResponse {
    Company company;
    ArrayList<String> workspaces;
    ArrayList<Group> groups;
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

    @Data
    private static class Position {
        private float id;
        private String name;
        private String description;
        @JsonProperty("deleted_at")
        private String deletedAt;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("company_id")
        private float companyId;
        @JsonProperty("position_type")
        private String positionType;
    }

    @Data
    private static class Company {
        private Integer id;
        private String name;
        @JsonProperty("parent_id")
        private String parentId;
        private String status;
        @JsonProperty("owner_id")
        private Integer ownerId;
        @JsonProperty("deleted_at")
        private String deletedAt;
        @JsonProperty("createdAt")
        private String createdAt;
        @JsonProperty("updatedAt")
        private String updatedAt;
    }

    @Data
    private static class Group {
        @JsonProperty("parent_path")
        ArrayList<ParentPath> parentPaths;
        private float id;
        private String name;
    }

    @Data
    private static class ParentPath {
        private Integer id;
        private String name;
    }
}
