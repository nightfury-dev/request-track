package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "final_results", catalog = "FTRR")
public class FinalResult implements java.io.Serializable {
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "traineeForRequests"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private TraineeForRequest traineeForRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User user;

    @Column(name = "result")
    private Integer result;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "finalResult")
    private PlanResource planResource;

    @Column(name = "rank")
    private Float rank;
}
