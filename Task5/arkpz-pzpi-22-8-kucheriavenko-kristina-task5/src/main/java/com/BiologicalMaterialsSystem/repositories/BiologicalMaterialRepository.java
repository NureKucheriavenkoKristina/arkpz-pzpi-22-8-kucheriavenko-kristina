package com.BiologicalMaterialsSystem.src.main.java.com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.BiologicalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiologicalMaterialRepository  extends JpaRepository<BiologicalMaterial, Long>{
}
