package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories.DeviceRepository;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    public DeviceService(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device addDevice(Device device) {
        deviceRepository.save(device);
        return device;
    }

    public void removeDevice(Device device) {
        deviceRepository.deleteById(device);
    }

    public Device getDevice(String deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
    }
}
