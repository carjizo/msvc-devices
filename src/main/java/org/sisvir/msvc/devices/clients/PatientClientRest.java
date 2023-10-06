package org.sisvir.msvc.devices.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msvc-users", url = "msvc-users-production.up.railway.app", path = "/patients")
public interface PatientClientRest {

    @DeleteMapping("/delete-device/{id}")
    void deletePatientDeviceById(@PathVariable Long id);
}
