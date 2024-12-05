package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageConditionRepository extends JpaRepository<StorageCondition, Long> {
}
