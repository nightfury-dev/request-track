package framgia.co.edu.ftrr.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "positions", catalog = "FTRR")
public class Position {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "deleted_at", length = 19)
    private String deletedAt;
    @Column(name = "created_at", length = 19)
    private String createdAt;
    @Column(name = "updated_at", length = 19)
    private String updatedAt;
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "position_type")
    private String positionType;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<User> user;
}
