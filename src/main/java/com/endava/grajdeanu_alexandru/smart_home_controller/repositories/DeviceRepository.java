package com.endava.grajdeanu_alexandru.smart_home_controller.repositories;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepository {
    private List<Device> devices;

    public DeviceRepository() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getAllDevices() {
        return devices;
    }
}
