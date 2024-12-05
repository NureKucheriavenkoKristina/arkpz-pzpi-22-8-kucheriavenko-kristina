package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonorService {
    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    // Створення нового донора
    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    // Отримання донора за ID
    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));
    }

    // Отримання всіх донорів
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    // Оновлення даних донора
    public Donor updateDonor(Long id, Donor newDonor) {
        Donor donor = getDonorById(id);
        donor.setFirstName(newDonor.getFirstName());
        donor.setLastName(newDonor.getLastName());
        donor.setBirthDate(newDonor.getBirthDate());
        donor.setGender(newDonor.getGender());
        donor.setIdNumber(newDonor.getIdNumber());
        donor.setBloodType(newDonor.getBloodType());
        donor.setTransplantRestrictions(newDonor.getTransplantRestrictions());
        return donorRepository.save(donor);
    }

    // Видалення донора
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

}
