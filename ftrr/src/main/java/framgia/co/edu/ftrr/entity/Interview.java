package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "interviews", catalog = "FTRR")
public class Interview implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "result_type_id")
    private Integer resultType;
    @Column(name = "title_interview_id")
    private Integer titleInterview;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_for_request_id")
    private TraineeForRequest traineeForRequest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @CreatedBy
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private User updatedBy;
    @Column(name = "content")
    private String content;
    @Column(name = "result")
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    @LastModifiedDate
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer")
    private User reviewer;
    @Column(name = "start_time", length = 19)
    private Date startTime;
    @Column(name = "end_time", length = 19)
    private Date endTime;
}
