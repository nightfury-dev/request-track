package framgia.co.edu.ftrr.entity;
// Generated Feb 12, 2019 2:57:27 PM by Hibernate Tools 5.1.10.Final

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Requests generated by hbm2java
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "requests", catalog = "FTRR")
public class Request implements java.io.Serializable {

	private Integer id;
	private User createdBy;
	private User updatedBy;
	private String division;
	private Integer quantity;
	private Date deadline;
	private String language;
	private Byte status;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private List<TraineeForRequest> traineeForRequests;

	public Request() {
	}

	public Request(User createdBy, User updatedBy, String division, Integer quantity, Date deadline,
			String language, Byte status, Timestamp createdAt, Timestamp updatedAt,
				   List<TraineeForRequest> traineeForRequests) {
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.division = division;
		this.quantity = quantity;
		this.deadline = deadline;
		this.language = language;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.traineeForRequests = traineeForRequests;
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
	@JoinColumn(name = "created_by")
	@CreatedBy
	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@LastModifiedBy
	public User getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "division")
	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline", length = 10)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "language")
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "created_at", length = 19)
	@CreatedDate
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 19)
	@UpdateTimestamp
	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requests")
	public List<TraineeForRequest> getTraineeForRequests() {
		return this.traineeForRequests;
	}

	public void setTraineeForRequests(List<TraineeForRequest> traineeForRequests) {
		this.traineeForRequests = traineeForRequests;
	}

}
