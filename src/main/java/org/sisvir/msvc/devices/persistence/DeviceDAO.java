package org.sisvir.msvc.devices.persistence;

import org.sisvir.msvc.devices.models.entities.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceDAO {

    List<Device> findAll();

    Optional<Device> findById(Long id);

    Optional<Device> findByMac(String mac);

    void create(Device device);

    void deleteById(Long id);

    List<Device> findAllById(Iterable<Long> ids);
}
