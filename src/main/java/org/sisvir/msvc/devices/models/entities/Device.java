package org.sisvir.msvc.devices.models.entities;


//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    @NotBlank
//    @NotNull -> para diferentes de String
    private String mac;

    @Size(max = 12)
    @NotBlank
    private String code;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Lima"); // Zona horaria de Perú
        createdAt = OffsetDateTime.now(zoneId);
        updatedAt = OffsetDateTime.now(zoneId);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId zoneId = ZoneId.of("America/Lima"); // Zona horaria de Perú
        updatedAt = OffsetDateTime.now(zoneId);
    }
}
