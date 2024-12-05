package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.enums.DonationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "biological_materials")
@Setter
@Getter
public class BiologicalMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialID;

    @Column(nullable = false)
    @NotBlank(message = "Material name cannot be blank") // Перевіряє, що рядок не порожній і не складається лише з пробілів
    private String materialName;

    @Column(nullable = false)
    @Future(message = "Expiration date must be in the future") // Перевіряє, що дата є в майбутньому
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Donation status cannot be null") // Перевіряє, що статус не є null
    private DonationStatus status;

    @Column(nullable = false)
    @PastOrPresent(message = "Transfer date must be in the past or present") // Перевіряє, що дата є в минулому або теперішньому
    private Date transferDate;

    @ManyToOne
    @JoinColumn(name = "donorID")
    @NotNull(message = "Donor cannot be null") // Перевіряє, що зв'язок із донором не є null
    private Donor donorID;
}
