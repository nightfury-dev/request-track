package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.NotificationDTO;
import framgia.co.edu.ftrr.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotificationUtils {

    private static final Logger logger = LoggerFactory.getLogger(NotificationUtils.class);

    public static NotificationDTO notificationToNotificationDTO(Notification notification) {
        try {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setUser(notification.getUser());
            notificationDTO.setUserRequest(notification.getUserRequest());

            return notificationDTO;
        } catch (Exception e) {
            logger.error("Error in NotificationToNotificationDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<NotificationDTO> listNotificationToListNotificationDTO(List<Notification> notifications) {
        try {
            return Optional.ofNullable(notifications).orElseGet(Collections::emptyList)
                    .stream().map(notification -> notificationToNotificationDTO(notification)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listNotificationToListNotificationDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Notification notificationDTOToNotification(NotificationDTO NotificationDTO) {
        try {
            Notification Notification = new Notification();
            BeanUtils.copyProperties(NotificationDTO, Notification);

            return Notification;
        } catch (Exception e) {
            logger.error("Error in NotificationDTOToNotification: " + e.getMessage());
            return null;
        }
    }

    public static List<Notification> listNotificationDTOToListNotification(List<NotificationDTO> notificationDTOs) {
        try {
            return Optional.ofNullable(notificationDTOs).orElseGet(Collections::emptyList)
                    .stream().map(notificationDTO -> notificationDTOToNotification(notificationDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listNotificationDTOToListNotification: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}