package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "notifications", catalog = "FTRR")
@EntityListeners(AuditingEntityListener.class)
public class Notification implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "content")
	private String content;

	@Column(name = "status", length = 45)
	private Integer status;

	@Column(name = "url_id")
	private Integer urlId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	@LastModifiedDate
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "read_at", length = 19)
	private Date readAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_request")
	@CreatedBy
	private User userRequest;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", length = 19)
	private Date deletedAt;
}
