package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "result_trainings", catalog = "FTRR")
public class ResultTraining implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id", unique = true)
    private Trainee trainee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private User updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @CreatedBy
    private User createdBy;

    @Column(name = "point")
    private Integer point;

    @Column(name = "content")
    private String content;

    @Column(name = "result")
    private String result;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    private Date createdAt;
}
