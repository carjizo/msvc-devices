package org.sisvir.msvc.devices.repositories;

import org.sisvir.msvc.devices.models.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Optional<Device> findByMac(String mac);
}
