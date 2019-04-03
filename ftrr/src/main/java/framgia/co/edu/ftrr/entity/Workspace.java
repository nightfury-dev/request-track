package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "workspaces", catalog = "FTRR")
public class Workspace {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "isEnable")
    private Boolean isEnable;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_time", length = 19)
    private Date openTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "close_time", length = 19)
    private Date closeTime;
    @Column(name = "owner_id")
    private String owner;
    @Column(name = "company_id")
    private String company;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    @LastModifiedDate
    private Date updatedAt;
    @Column(name = "time_zone")
    private String timeZone;
    @Column(name = "address")
    private String address;
    @ManyToMany(mappedBy = "workspaces")
    private List<User> users;
}
