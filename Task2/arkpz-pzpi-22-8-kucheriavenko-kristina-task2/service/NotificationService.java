package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Створення нової сповіщення
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Отримання сповіщення за ID
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    // Отримання всіх сповіщень
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Оновлення сповіщення
    public Notification updateNotification(Long id, Notification newNotification) {
        Notification notification = getNotificationById(id);
        notification.setEventTime(newNotification.getEventTime());
        notification.setEventType(newNotification.getEventType());
        notification.setDetails(newNotification.getDetails());
        notification.setStatus(newNotification.getStatus());
        notification.setMaterialID(newNotification.getMaterialID());
        return notificationRepository.save(notification);
    }

    // Видалення сповіщення
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
