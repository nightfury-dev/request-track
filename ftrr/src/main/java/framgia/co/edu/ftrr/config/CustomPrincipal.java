package framgia.co.edu.ftrr.config;

import framgia.co.edu.ftrr.entity.Group;
import framgia.co.edu.ftrr.entity.Position;
import lombok.Data;

import java.util.List;

/**
 * User được custom lại để luuw các thông tin phân quyền của người dùng trong spring security và add vào jwt token
 */
@Data
public class CustomPrincipal{
    private String username;
    private String password;
    private Position position;
    private List<Group> groups;
    private int role;
}
