package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "plan_resources", catalog = "FTRR")
public class PlanResource implements java.io.Serializable {
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "finalResults"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private FinalResult finalResult;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	private Date date;

	@Column(name = "mentor")
	private String mentor;

	@Column(name = "trainee_id")
	private Integer traineeId;
}
