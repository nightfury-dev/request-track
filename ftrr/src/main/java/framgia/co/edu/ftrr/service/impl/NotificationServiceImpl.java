package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.NotificationStatus;
import framgia.co.edu.ftrr.dto.request.NotificationDTO;
import framgia.co.edu.ftrr.entity.Notification;
import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.entity.ScopeTraining;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.NotificationRepository;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.repository.ScopeTrainingRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.NotificationService;
import framgia.co.edu.ftrr.util.NotificationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    ScopeTrainingRepository scopeTrainingRepository;
    @Autowired
    UserRepository userRepository;
    @Value("notification to trainer")
    String notificationContent;

    @Override
    @Transactional
    public List<NotificationDTO> sendNotification(Integer requestId) {
        try {
            List<Notification> listNotification = new ArrayList<>();
            List<User> listUser = getListTrainerToNotify(requestId);
            for (User user : listUser) {
                Notification notification = new Notification();
                notification.setContent(notificationContent);
                notification.setStatus(NotificationStatus.UNSEEN.getCode());
                notification.setUser(user);
                listNotification.add(notification);
            }
            return NotificationUtils.listNotificationToListNotificationDTO(notificationRepository.saveAll(listNotification));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<User> getListTrainerToNotify(Integer requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if (request == null || StringUtils.isBlank(request.getLanguage())) return Collections.emptyList();
        List<ScopeTraining> scopeTrainings = scopeTrainingRepository.getAllByLanguage(request.getLanguage());
        return scopeTrainings.stream().map(ScopeTraining::getUser).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getListNotificationByUser(String email) {
        try {
            List<Notification> list = notificationRepository.findAllByUser(email);
            return NotificationUtils.listNotificationToListNotificationDTO(list);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
