package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.controllers;

import java.util.List;
import jakarta.validation.Valid;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/storage-conditions")
public class StorageConditionController {

    private final StorageConditionService service;

    public StorageConditionController(StorageConditionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StorageCondition> createCondition(@Valid @RequestBody StorageCondition condition, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(service.createCondition(condition));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageCondition> getConditionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getConditionById(id));
    }

    @GetMapping
    public ResponseEntity<List<StorageCondition>> getAllConditions() {
        return ResponseEntity.ok(service.getAllConditions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageCondition> updateCondition(@PathVariable Long id, @Valid @RequestBody StorageCondition condition, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(service.updateCondition(id, condition));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        service.deleteCondition(id);
        return ResponseEntity.noContent().build();
    }
}
