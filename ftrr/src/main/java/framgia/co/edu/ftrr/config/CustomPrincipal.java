package framgia.co.edu.ftrr.config;

import framgia.co.edu.ftrr.entity.Group;
import framgia.co.edu.ftrr.entity.Position;
import lombok.Data;

import java.util.List;

@Data
public class CustomPrincipal{
    private String username;
    private String password;
    private Position position;
    private List<Group> groups;
    private int role;
}
