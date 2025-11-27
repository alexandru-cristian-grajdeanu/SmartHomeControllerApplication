package com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryDeviceRepository implements DeviceRepository {
    private final Map<String, Device> devices = new HashMap<>();

    @Override
    public void save(Device device) {
        devices.put(device.getId(), device);
    }

    @Override
    public List<Device> findAll() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public void deleteById(Device device) {
        devices.remove(device.getId());
    }

    @Override
    public Optional<Device> findById(String id) {
        return Optional.ofNullable(devices.get(id));
    }
}
