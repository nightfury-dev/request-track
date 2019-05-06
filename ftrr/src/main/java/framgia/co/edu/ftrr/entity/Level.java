package framgia.co.edu.ftrr.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "levels", catalog = "FTRR")
public class Level implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", length = 100)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", length = 19)
	private Date deletedAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "level")
	private List<Trainee> trainees;
}
