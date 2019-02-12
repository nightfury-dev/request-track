package framgia.co.edu.ftrr.entity;
// Generated Feb 12, 2019 2:57:27 PM by Hibernate Tools 5.1.10.Final

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TraineeForRequests generated by hbm2java
 */
@Entity
@Table(name = "trainee_for_requests", catalog = "FTRR")
public class TraineeForRequest implements java.io.Serializable {

	private Integer id;
	private Request request;
	private User user;
	private Integer traineeId;
	private Byte status;
	private Date createdAt;
	private Date updatedAt;
	private List<ResultInterview> resultInterviewses;
	private FinalResult finalResult;

	public TraineeForRequest() {
	}

	public TraineeForRequest(Request request, User user, Integer traineeId, Byte status, Date createdAt,
			Date updatedAt, List<ResultInterview> resultInterviewses, FinalResult finalResult) {
		this.request = request;
		this.user = user;
		this.traineeId = traineeId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.resultInterviewses = resultInterviewses;
		this.finalResult = finalResult;
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
	@JoinColumn(name = "request_id")
	public Request getRequests() {
		return this.request;
	}

	public void setRequests(Request request) {
		this.request = request;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	public User getUsers() {
		return this.user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	@Column(name = "trainee_id")
	public Integer getTraineeId() {
		return this.traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainees")
	public List<ResultInterview> getResultInterviewses() {
		return this.resultInterviewses;
	}

	public void setResultInterviewses(List<ResultInterview> resultInterviewses) {
		this.resultInterviewses = resultInterviewses;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "traineeForRequests")
	public FinalResult getFinalResults() {
		return this.finalResult;
	}

	public void setFinalResults(FinalResult finalResult) {
		this.finalResult = finalResult;
	}

}