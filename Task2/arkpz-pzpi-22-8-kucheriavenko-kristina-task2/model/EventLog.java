package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "eventLogs")
@Getter
@Setter
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventLogID;

    @NotNull(message = "Action cannot be null")
    @Size(min = 2, max = 255, message = "Action must be between 2 and 255 characters")
    @Column(nullable = false)
    private String action;

    @NotNull(message = "Action time cannot be null")
    @PastOrPresent(message = "Action time must be in the past or present")
    @Column(nullable = false)
    private Date actionTime;

    @NotNull(message = "Details cannot be null")
    @Size(min = 5, max = 1000, message = "Details must be between 5 and 1000 characters")
    @Column(nullable = false)
    private String details;

    @ManyToOne
    @JoinColumn(name = "userID")
    @NotNull(message = "Creator (user) cannot be null")
    private User creatorID;

    @ManyToOne
    @JoinColumn(name = "materialID")
    @NotNull(message = "Biological material cannot be null")
    private BiologicalMaterial materialID;

    @OneToMany
    @JoinColumn(name = "reportID")
    private List<Report> reports;
}
