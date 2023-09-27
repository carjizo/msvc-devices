package org.sisvir.msvc.devices.services.impl;

import org.sisvir.msvc.devices.clients.PatientClientRest;
import org.sisvir.msvc.devices.models.entities.Device;
import org.sisvir.msvc.devices.persistence.DeviceDAO;
import org.sisvir.msvc.devices.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private PatientClientRest clientRest;

    @Override
    public List<Device> findAll() {
        return deviceDAO.findAll();
    }

    @Override
    public Optional<Device> findById(Long id) {
        return deviceDAO.findById(id);
    }

    @Override
    public void create(Device device) {
        deviceDAO.create(device);
    }

    @Override
    public void deleteById(Long id) {
        deviceDAO.deleteById(id);
        clientRest.deletePatientDeviceById(id);
    }

    @Override
    public List<Device> findAllById(Iterable<Long> ids) {
        return deviceDAO.findAllById(ids);
    }

}
