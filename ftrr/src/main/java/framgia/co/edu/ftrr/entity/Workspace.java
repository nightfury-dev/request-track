package framgia.co.edu.ftrr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "workspaces", catalog = "FTRR")
public class Workspace implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "isEnable")
    private Boolean isEnable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_time", length = 19)
    @JsonProperty("open_time")
    private Date openTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "close_time", length = 19)
    @JsonProperty("close_time")
    private Date closeTime;

    @Column(name = "owner_id")
    @JsonProperty("owner_id")
    private String owner;

    @Column(name = "company_id")
    @JsonProperty("company_id")
    private String company;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    @JsonProperty("created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    @LastModifiedDate
    @JsonProperty("updated_at")
    private Date updatedAt;

    @Column(name = "timezone")
    private String timeZone;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "workspaces")
    @JsonIgnore
    private List<User> users;
}
