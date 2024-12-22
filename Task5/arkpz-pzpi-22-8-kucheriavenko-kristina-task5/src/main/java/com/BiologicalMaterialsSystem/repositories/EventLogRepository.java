package com.BiologicalMaterialsSystem.src.main.java.com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository  extends JpaRepository<EventLog, Long> {
}
