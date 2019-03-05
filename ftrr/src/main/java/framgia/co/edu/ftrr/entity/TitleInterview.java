package framgia.co.edu.ftrr.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "title_interviews", catalog = "FTRR")
public class TitleInterview implements java.io.Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", length = 19)
    private Date deletedAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "titleInterview")
    private List<ResultInterview> resultInterviews;
}
