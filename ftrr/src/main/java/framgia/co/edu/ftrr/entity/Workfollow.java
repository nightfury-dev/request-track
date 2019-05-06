package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "workfollows", catalog = "FTRR")
public class Workfollow implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Byte status;

    @Column(name = "steps")
    private String steps;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", length = 19)
    private Date deletedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    @LastModifiedDate
    private Date updatedAt;
}
