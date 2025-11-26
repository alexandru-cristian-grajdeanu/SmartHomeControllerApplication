package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void registerDevice(Device device) {
        deviceRepository.addDevice(device);
    }

    public void activateDevice(Device device) {
        if(deviceRepository.getAllDevices().contains(device)) {
            device.setActive(true);
        }else{
            throw new IllegalArgumentException("Device not registered in the system.");
        }
    }

    public void deactivateDevice(Device device) {
        if(deviceRepository.getAllDevices().contains(device)) {
            device.setActive(false);
        }else{
            throw new IllegalArgumentException("Device not registered in the system.");
        }
    }

    public void armSecuritySystem(Device device, String password) {
        if(device instanceof AlarmSystem alarmSystem){
            if(deviceRepository.getAllDevices().contains(device)) {
                if(alarmSystem.getPassword().equals(password)) {
                    alarmSystem.setActive(true);
                } else {
                    throw new IllegalArgumentException("Incorrect password for arming the security system.");
                }
            }
        }else{
            throw new IllegalArgumentException("The provided device is not a security system.");
        }
    }

    public void disarmSecuritySystem(Device device, String password) {
        if(device instanceof AlarmSystem alarmSystem){
            if(deviceRepository.getAllDevices().contains(device)) {
                if(alarmSystem.getPassword().equals(password)) {
                    alarmSystem.setActive(false);
                } else {
                    throw new IllegalArgumentException("Incorrect password for disarming the security system.");
                }
            }
        }else{
            throw new IllegalArgumentException("The provided device is not a security system.");
        }
    }
}
