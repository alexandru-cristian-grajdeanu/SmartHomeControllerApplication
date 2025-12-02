package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories.DeviceRepository;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class SmartHomeService {
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    public SmartHomeService(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public boolean armAlarmSystem(String password) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(password)) {
                if (alarmSystem.isArmed()) {
                    throw new RuntimeException("Alarm system is already armed");
                }
                alarmSystem.armSystem();
                deviceRepository.save(alarmSystem);
                return true;
            } else {
                throw new RuntimeException("Incorrect password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public boolean disarmAlarmSystem(String password) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(password)) {
                if (!alarmSystem.isArmed()) {
                    throw new RuntimeException("Alarm system is already disarmed");
                }
                alarmSystem.disarmSystem();
                deviceRepository.save(alarmSystem);
                return true;
            } else {
                throw new RuntimeException("Incorrect password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public boolean turnOffDevicesInRoom(String roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            device.turnOff();
            deviceRepository.save(device);

        });
        return true;
    }

    public boolean turnOnDevicesInRoom(String roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            device.turnOn();
            deviceRepository.save(device);

        });
        return true;
    }

    public void changeAlarmPassword(String oldPassword, String newPassword) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(oldPassword)) {
                alarmSystem.changePassword(newPassword);
                deviceRepository.save(alarmSystem);
            } else {
                throw new RuntimeException("Incorrect old password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public void changeTemperatureInRoom(String roomId, double newTemperature) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        var thermostatId = room.getDeviceIds().stream().filter(id -> id.startsWith("THERMOSTAT_")).findFirst()
                .orElseThrow(() -> new RuntimeException("Thermostat not found in room"));
        var thermostat = deviceRepository.findById(thermostatId).orElseThrow(() -> new RuntimeException("Thermostat device not found"));
        if (thermostat instanceof Thermostat thermostatDevice) {
            thermostatDevice.setTemperature(newTemperature);
            deviceRepository.save(thermostatDevice);
        }else{
            throw new RuntimeException("Device is not a Thermostat");
        }
        roomRepository.save(room);
    }

    public void closeLightsInRoom(String roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        var lightbulbId = room.getDeviceIds().stream().filter(id -> id.startsWith("LIGHTBULB")).findFirst().orElseThrow(() -> new RuntimeException("Lightbulb device not found"));
        var lightbulb = deviceRepository.findById(lightbulbId).orElseThrow(() -> new RuntimeException("Lightbulb device not found"));
        if(lightbulb instanceof LightBulb lightbulbDevice) {
            lightbulbDevice.turnOff();
            deviceRepository.save(lightbulbDevice);
        }else{
            throw new RuntimeException("Device is not a LightBulb");
        }
        roomRepository.save(room);
    }

    public void openLightsInRoom(String roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        var lightbulbId = room.getDeviceIds().stream().filter(id -> id.startsWith("LIGHTBULB")).findFirst().orElseThrow(() -> new RuntimeException("Lightbulb device not found"));
        var lightbulb = deviceRepository.findById(lightbulbId).orElseThrow(() -> new RuntimeException("Lightbulb device not found"));
        if(lightbulb instanceof LightBulb lightbulbDevice) {
            lightbulbDevice.turnOn();
            deviceRepository.save(lightbulbDevice);
        }
        else{
            throw new RuntimeException("Device is not a LightBulb");
        }
        roomRepository.save(room);
    }

    public void callAssistant() {
        var device = deviceRepository.findAll().stream().filter(d -> d.getId().startsWith("VOICE_ASSISTANT")).findFirst()
                .orElseThrow(() -> new RuntimeException("Voice Assistant device not found"));
        if(device instanceof SmartAssistant smartAssistant){
            smartAssistant.helloService();
        }
    }




}
