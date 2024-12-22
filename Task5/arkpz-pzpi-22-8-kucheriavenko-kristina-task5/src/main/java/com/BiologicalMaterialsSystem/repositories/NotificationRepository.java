package com.BiologicalMaterialsSystem.src.main.java.com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
