package org.sisvir.msvc.devices.persistence.impl;

import org.sisvir.msvc.devices.models.entities.Device;
import org.sisvir.msvc.devices.persistence.DeviceDAO;
import org.sisvir.msvc.devices.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class DeviceDAOImpl implements DeviceDAO {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Device> findAll() {
        return (List<Device>) deviceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    @Transactional
    public void create(Device device) {
        deviceRepository.save(device);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> findAllById(Iterable<Long> ids) {
        return (List<Device>) deviceRepository.findAllById(ids);
    }
}
