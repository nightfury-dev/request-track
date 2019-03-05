package framgia.co.edu.ftrr.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "result_interviews", catalog = "FTRR")
public class ResultInterview implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "result_type_id")
	private ResultType resultType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_interview_id")
	private TitleInterview titleInterview;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainee_for_request_id")
	private TraineeForRequest traineeForRequest;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private User createdBy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	private User updatedBy;
	@Column(name = "content")
	private String content;
	@Column(name = "result")
	private String result;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	private Date updatedAt;
}
