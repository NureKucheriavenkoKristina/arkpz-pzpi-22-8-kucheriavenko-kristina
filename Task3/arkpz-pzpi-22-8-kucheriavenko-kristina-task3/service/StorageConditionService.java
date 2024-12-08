package com.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.repositories.StorageConditionRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageConditionService {
    private final StorageConditionRepository repository;
    private final EventLogService eventLogService;

    public void createCondition(User user, StorageCondition condition) {
        repository.save(condition);
        eventLogService.logAction(user, "Added new storage condition with ID: " + condition.getRecordID());
    }

    public StorageCondition getConditionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condition not found"));
    }

    public List<StorageCondition> getAllConditions() {
        return repository.findAll();
    }

    public void updateCondition(User user, Long id, StorageCondition newCondition) {
        StorageCondition condition = getConditionById(id);
        condition.setTemperature(newCondition.getTemperature());
        condition.setOxygenLevel(newCondition.getOxygenLevel());
        condition.setHumidity(newCondition.getHumidity());
        condition.setMeasurementTime(newCondition.getMeasurementTime());
        condition.setMaterialID(newCondition.getMaterialID());
        repository.save(condition);
        eventLogService.logAction(user, "Updated storage condition with ID: " + condition.getRecordID());
    }

    public void deleteCondition(User user, Long id) {
        repository.deleteById(id);
        eventLogService.logAction(user, "Deleted storage condition with ID: " + id);
    }
}
