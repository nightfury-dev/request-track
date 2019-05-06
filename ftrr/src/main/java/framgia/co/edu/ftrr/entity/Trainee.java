package framgia.co.edu.ftrr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "trainees", catalog = "FTRR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "trainees"})
public class Trainee implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    @JsonIgnore
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    @JsonIgnore
    private User updatedBy;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_time", length = 19)
    private Date startedTime;

    @Column(name = "office", length = 50)
    private String office;

    @Column(name = "status", length = 45)
    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    @LastModifiedDate
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", length = 19)
    private Date deletedAt;

    @Column(name = "language")
    private String language;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainee")
    @JsonIgnore
    private List<TraineeForRequest> traineeForRequests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    @JsonIgnore
    private User trainer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainee")
    @JsonIgnore
    private List<ResultTraining> resultTrainings;
}
