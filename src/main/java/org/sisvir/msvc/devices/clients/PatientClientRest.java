package org.sisvir.msvc.devices.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-users", url = "localhost:8003", path = "/patients")
public interface PatientClientRest {

    @DeleteMapping("/delete-device/{id}")
    void deletePatientDeviceById(@PathVariable Long id);
}
