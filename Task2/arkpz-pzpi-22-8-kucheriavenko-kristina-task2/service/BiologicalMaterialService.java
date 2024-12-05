package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class BiologicalMaterialService {

    private final BiologicalMaterialRepository biologicalMaterialRepository;

    public BiologicalMaterialService(BiologicalMaterialRepository biologicalMaterialRepository) {
        this.biologicalMaterialRepository = biologicalMaterialRepository;
    }

    // Створення нового біологічного матеріалу
    public BiologicalMaterial createBiologicalMaterial(BiologicalMaterial biologicalMaterial) {
        return biologicalMaterialRepository.save(biologicalMaterial);
    }

    // Отримання біологічного матеріалу за ID
    public BiologicalMaterial getBiologicalMaterialById(Long id) {
        return biologicalMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biological Material not found"));
    }

    // Отримання всіх біологічних матеріалів
    public List<BiologicalMaterial> getAllBiologicalMaterials() {
        return biologicalMaterialRepository.findAll();
    }

    // Оновлення біологічного матеріалу
    public BiologicalMaterial updateBiologicalMaterial(Long id, BiologicalMaterial newBiologicalMaterial) {
        BiologicalMaterial biologicalMaterial = getBiologicalMaterialById(id);
        biologicalMaterial.setMaterialName(newBiologicalMaterial.getMaterialName());
        biologicalMaterial.setExpirationDate(newBiologicalMaterial.getExpirationDate());
        biologicalMaterial.setStatus(newBiologicalMaterial.getStatus());
        biologicalMaterial.setTransferDate(newBiologicalMaterial.getTransferDate());
        biologicalMaterial.setDonorID(newBiologicalMaterial.getDonorID());
        return biologicalMaterialRepository.save(biologicalMaterial);
    }

    // Видалення біологічного матеріалу
    public void deleteBiologicalMaterial(Long id) {
        biologicalMaterialRepository.deleteById(id);
    }

}