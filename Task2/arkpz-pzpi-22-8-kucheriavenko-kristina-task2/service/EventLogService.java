package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EventLogService {


    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    // Створення нового журналу подій
    public EventLog createEventLog(EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }

    // Отримання журналу подій за ID
    public EventLog getEventLogById(Long id) {
        return eventLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Log not found"));
    }

    // Отримання всіх журналів подій
    public List<EventLog> getAllEventLogs() {
        return eventLogRepository.findAll();
    }

    // Оновлення журналу подій
    public EventLog updateEventLog(Long id, EventLog newEventLog) {
        EventLog eventLog = getEventLogById(id);
        eventLog.setAction(newEventLog.getAction());
        eventLog.setActionTime(newEventLog.getActionTime());
        eventLog.setDetails(newEventLog.getDetails());
        eventLog.setCreatorID(newEventLog.getCreatorID());
        eventLog.setMaterialID(newEventLog.getMaterialID());
        eventLog.setReports(newEventLog.getReports());
        return eventLogRepository.save(eventLog);
    }

    // Видалення журналу подій
    public void deleteEventLog(Long id) {
        eventLogRepository.deleteById(id);
    }
}
