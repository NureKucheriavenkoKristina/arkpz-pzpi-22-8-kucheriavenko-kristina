package com.BiologicalMaterialsSystem.src.main.java.com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository  extends JpaRepository<Donor, Long> {
}
