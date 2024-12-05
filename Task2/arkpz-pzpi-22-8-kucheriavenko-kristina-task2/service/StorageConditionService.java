package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StorageConditionService {

    private final StorageConditionRepository repository;

    public StorageConditionService(StorageConditionRepository repository) {
        this.repository = repository;
    }

    public StorageCondition createCondition(StorageCondition condition) {
        return repository.save(condition);
    }

    public StorageCondition getConditionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condition not found"));
    }

    public List<StorageCondition> getAllConditions() {
        return repository.findAll();
    }

    public StorageCondition updateCondition(Long id, StorageCondition newCondition) {
        StorageCondition condition = getConditionById(id);
        condition.setTemperature(newCondition.getTemperature());
        condition.setOxygenLevel(newCondition.getOxygenLevel());
        condition.setHumidity(newCondition.getHumidity());
        condition.setMeasurementTime(newCondition.getMeasurementTime());
        condition.setMaterialID(newCondition.getMaterialID());
        return repository.save(condition);
    }

    public void deleteCondition(Long id) {
        repository.deleteById(id);
    }
}
