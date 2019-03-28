package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> sendNotification(Integer requestId);

    List<NotificationDTO> getListNotificationByUser(String email);

}
