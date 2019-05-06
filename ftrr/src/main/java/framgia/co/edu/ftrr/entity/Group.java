package framgia.co.edu.ftrr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "groups", catalog = "FTRR")
public class Group implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "parent_path", length = 100)
    private String parentPath;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<User> users;

    @JsonProperty("parent_path")
    @Transient
    private List<Group> groups;
}
