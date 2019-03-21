package framgia.co.edu.ftrr.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", catalog = "FTRR")
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "email", length = 225)
    private String email;
    @Column(name = "code", length = 45)
    private String code;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "division", length = 45)
    private Integer division;
    @Column(name = "phone", length = 45)
    private String phone;
    @Column(name = "gender", length = 6)
    private String gender;
    @Column(name = "role", length = 2)
    private Integer role;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
    private Date updatedAt;
    @Column(name = "encrypted_password", length = 45)
    private String encryptedPassword;
    @Column(name = "reset_password_token", length = 45)
    private String resetPasswordToken;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reset_password_sent_at", length = 19)
    private Date resetPasswordSentAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "remember_created_at", length = 19)
    private Date rememberCreatedAt;
    @Column(name = "confirmation_token", length = 45)
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "confirmation_at", length = 19)
    private Date confirmationAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "confirmation_sent_at", length = 19)
    private Date confirmationSentAt;
}
