package com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.StorageCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageConditionRepository extends JpaRepository<StorageCondition, Long> {
}
