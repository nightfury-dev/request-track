package framgia.co.edu.ftrr.entity;
// Generated Feb 12, 2019 2:57:27 PM by Hibernate Tools 5.1.10.Final

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * ResultInterviews generated by hbm2java
 */
@Entity
@Table(name = "result_interviews", catalog = "FTRR")
public class ResultInterview implements java.io.Serializable {

	private Integer id;
	private ResultType resultType;
	private TitleInterview titleInterview;
	private TraineeForRequest traineeForRequest;
	private User createdBy;
	private User updatedBy;
	private String content;
	private String result;
	private Date createdAt;
	private Date updatedAt;

	public ResultInterview() {
	}

	public ResultInterview(ResultType resultType, TitleInterview titleInterview, TraineeForRequest traineeForRequest,
			User createdBy, User updatedBy, String content, String result, Date createdAt,
			Date updatedAt) {
		this.resultType = resultType;
		this.titleInterview = titleInterview;
		this.traineeForRequest = traineeForRequest;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.content = content;
		this.result = result;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "result_type_id")
	public ResultType getResultTypes() {
		return this.resultType;
	}

	public void setResultTypes(ResultType resultType) {
		this.resultType = resultType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_interview_id")
	public TitleInterview getTitleInterviews() {
		return this.titleInterview;
	}

	public void setTitleInterviews(TitleInterview titleInterview) {
		this.titleInterview = titleInterview;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainee_for_request_id")
	public TraineeForRequest getTraineeForRequest() {
		return this.traineeForRequest;
	}

	public void setTraineeForRequest(TraineeForRequest traineeForRequest) {
		this.traineeForRequest = traineeForRequest;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	public User getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "result")
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
