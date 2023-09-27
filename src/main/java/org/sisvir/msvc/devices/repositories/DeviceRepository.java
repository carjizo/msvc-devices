package org.sisvir.msvc.devices.repositories;

import org.sisvir.msvc.devices.models.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
