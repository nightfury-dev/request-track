package framgia.co.edu.ftrr.controller;

import framgia.co.edu.ftrr.dto.request.NotificationDTO;
import framgia.co.edu.ftrr.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> loadNotificationByCurrentUser(Authentication authentication) {
        List<NotificationDTO> notifications = notificationService.getListNotificationByUser(authentication.getName());
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
