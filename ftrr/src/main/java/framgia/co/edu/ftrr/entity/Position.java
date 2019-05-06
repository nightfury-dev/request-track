package framgia.co.edu.ftrr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "positions", catalog = "FTRR")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "code", unique = true, nullable = false)
    private Integer code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted_at", length = 19)
    @JsonProperty("deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "created_at", length = 19)
    @JsonProperty("created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", length = 19)
    @JsonProperty("updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "company_id")
    @JsonProperty("company_id")
    private Integer companyId;

    @Column(name = "position_type")
    @JsonProperty("position_type")
    private String positionType;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    @JsonIgnore
    private List<User> user;
}
