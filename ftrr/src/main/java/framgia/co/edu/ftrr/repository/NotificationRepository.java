package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select a from Notification a inner join a.user b where b.email = :email order by a.status desc ,a.createdAt desc ")
    List<Notification> findAllByUser(@Param("email") String email);
}