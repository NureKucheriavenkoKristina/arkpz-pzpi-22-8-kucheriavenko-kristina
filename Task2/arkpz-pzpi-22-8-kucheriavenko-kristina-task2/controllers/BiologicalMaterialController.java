package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.controllers;

import java.util.List;
import jakarta.validation.Valid;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/biological-materials")
public class BiologicalMaterialController {

    private final BiologicalMaterialService biologicalMaterialService;

    public BiologicalMaterialController(BiologicalMaterialService biologicalMaterialService) {
        this.biologicalMaterialService = biologicalMaterialService;
    }

    @PostMapping
    public ResponseEntity<BiologicalMaterial> createBiologicalMaterial(
            @Valid @RequestBody BiologicalMaterial biologicalMaterial, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(biologicalMaterialService.createBiologicalMaterial(biologicalMaterial));
    }

    @GetMapping("/{materialID}")
    public ResponseEntity<BiologicalMaterial> getBiologicalMaterialById(@PathVariable("materialID") Long materialID) {
        return ResponseEntity.ok(biologicalMaterialService.getBiologicalMaterialById(materialID));
    }

    @GetMapping
    public ResponseEntity<List<BiologicalMaterial>> getAllBiologicalMaterials() {
        return ResponseEntity.ok(biologicalMaterialService.getAllBiologicalMaterials());
    }

    @PutMapping("/{materialID}")
    public ResponseEntity<BiologicalMaterial> updateBiologicalMaterial(
            @PathVariable("materialID") Long materialID,
            @Valid @RequestBody BiologicalMaterial biologicalMaterial,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(biologicalMaterialService.updateBiologicalMaterial(materialID, biologicalMaterial));
    }

    @DeleteMapping("/{materialID}")
    public ResponseEntity<Void> deleteBiologicalMaterial(@PathVariable("materialID") Long materialID) {
        biologicalMaterialService.deleteBiologicalMaterial(materialID);
        return ResponseEntity.noContent().build();
    }
}
