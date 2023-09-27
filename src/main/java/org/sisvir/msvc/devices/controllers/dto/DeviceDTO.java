package org.sisvir.msvc.devices.controllers.dto;

//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

    private Long id;

    @NotBlank
    private String mac;

    @NotBlank
    private String code;
}
