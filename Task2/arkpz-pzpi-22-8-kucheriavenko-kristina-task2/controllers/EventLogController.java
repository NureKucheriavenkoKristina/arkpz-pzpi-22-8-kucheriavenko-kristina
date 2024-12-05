package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.controllers;

import java.util.List;
import jakarta.validation.Valid;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-logs")
public class EventLogController {

    private final EventLogService eventLogService;

    public EventLogController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @PostMapping
    public ResponseEntity<EventLog> createEventLog(@Valid @RequestBody EventLog eventLog, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(eventLogService.createEventLog(eventLog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventLog> getEventLogById(@PathVariable Long id) {
        return ResponseEntity.ok(eventLogService.getEventLogById(id));
    }

    @GetMapping
    public ResponseEntity<List<EventLog>> getAllEventLogs() {
        return ResponseEntity.ok(eventLogService.getAllEventLogs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventLog> updateEventLog(@PathVariable Long id, @Valid @RequestBody EventLog eventLog, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(eventLogService.updateEventLog(id, eventLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventLog(@PathVariable Long id) {
        eventLogService.deleteEventLog(id);
        return ResponseEntity.noContent().build();
    }
}
