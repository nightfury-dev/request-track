package framgia.co.edu.ftrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import framgia.co.edu.ftrr.entity.Notification;

public interface NotificationRepository  extends JpaRepository<Notification, Integer> {
}