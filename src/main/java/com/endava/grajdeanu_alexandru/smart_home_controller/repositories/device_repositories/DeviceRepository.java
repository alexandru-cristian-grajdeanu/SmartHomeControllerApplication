package com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository {
    void save(Device device);
    List<Device> findAll();
    void deleteById(Device device);
    Optional<Device> findById(String id);
}
