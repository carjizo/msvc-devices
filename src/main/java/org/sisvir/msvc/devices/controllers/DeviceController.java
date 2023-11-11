package org.sisvir.msvc.devices.controllers;

import feign.FeignException;
//import jakarta.validation.Valid;
import org.sisvir.msvc.devices.controllers.dto.DeviceDTO;
import org.sisvir.msvc.devices.models.entities.Device;
import org.sisvir.msvc.devices.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getAll")
    @CrossOrigin
    public ResponseEntity<?> listar() {

        List<DeviceDTO> deviceDTOList = deviceService.findAll()
                .stream()
                .map(device -> DeviceDTO.builder()
                        .id(device.getId())
                        .mac(device.getMac())
                        .code(device.getCode())
                        .build())
                .toList();
        return ResponseEntity.ok(deviceDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailById(@PathVariable Long id) {

        Optional<Device> deviceOptional = deviceService.findById(id);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            DeviceDTO deviceDTO = DeviceDTO.builder()
                    .id(device.getId())
                    .mac(device.getMac())
                    .code(device.getCode())
                    .build();
            return ResponseEntity.ok(deviceDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/mac/{mac}")
    public ResponseEntity<?> detailByMac(@PathVariable String mac) {

        Optional<Device> deviceOptional = deviceService.findByMac(mac);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            DeviceDTO deviceDTO = DeviceDTO.builder()
                    .id(device.getId())
                    .mac(device.getMac())
                    .code(device.getCode())
                    .build();
            return ResponseEntity.ok(deviceDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@Valid @RequestBody DeviceDTO deviceDTO, BindingResult result) throws URISyntaxException {

        if (result.hasErrors()) {
            return validate(result);
        }

        deviceService.create(Device.builder()
                    .mac(deviceDTO.getMac())
                    .code(deviceDTO.getCode())
                .build());
        return ResponseEntity.created(new URI("/devices/create")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@Valid @RequestBody DeviceDTO deviceDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validate(result);
        }


        Optional<Device> deviceOptional =  deviceService.findById(id);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            device.setMac(deviceDTO.getMac());
            device.setCode(deviceDTO.getCode());
            deviceService.create(device);
            return ResponseEntity.ok("Registro Actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Device> deviceOptional = deviceService.findById(id);
        if (deviceOptional.isPresent()) {
            deviceService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAllById")
    public ResponseEntity<?> findAllById(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(deviceService.findAllById(ids));
    }

    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
