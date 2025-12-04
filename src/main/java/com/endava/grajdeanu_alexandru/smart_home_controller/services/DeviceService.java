package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.inputDTOs.*;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs.*;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
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

    public List<DeviceResponseDTO> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().map(device -> new DeviceResponseDTO(device.getId(), device.getClass().getSimpleName())).toList();
    }

    public DeviceResponseDTO addDevice(DeviceDTO device) {
        Device createdDevice;
        switch (device) {
            case SmartHomeAssistantDTO smartHomeAssistantDTO -> {
                createdDevice = new SmartAssistant(smartHomeAssistantDTO.getNameOfAssistant());
                deviceRepository.save(createdDevice);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getSimpleName());
            }
            case LightBulbDTO lightBulbDTO -> {
                if (lightBulbDTO.getRoomId() == null || roomRepository.findById(lightBulbDTO.getRoomId()).isEmpty()) {
                    throw new RuntimeException("Room not found for LightBulb");
                }
                createdDevice = new LightBulb(lightBulbDTO.getIntensity(), lightBulbDTO.getRoomId(), lightBulbDTO.getNameOfLightBulb());
                Room room = roomRepository.findById(lightBulbDTO.getRoomId()).get();
                room.getDeviceIds().add(createdDevice.getId());
                deviceRepository.save(createdDevice);
                roomRepository.save(room);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getName());
            }
            case ThermostatDTO thermostatDTO -> {
                if (thermostatDTO.getRoomId() == null || roomRepository.findById(thermostatDTO.getRoomId()).isEmpty()) {
                    throw new RuntimeException("Room not found for Thermostat");
                }
                if (deviceRepository.findAll().stream().filter(d -> d instanceof Thermostat).anyMatch(d -> {
                    Thermostat t = (Thermostat) d;
                    return t.getRoomId().equals(thermostatDTO.getRoomId());
                })) {
                    throw new RuntimeException("Thermostat already exists in this room");
                }
                Room room = roomRepository.findById(thermostatDTO.getRoomId()).get();
                createdDevice = new Thermostat(thermostatDTO.getRoomId());
                room.getDeviceIds().add(createdDevice.getId());
                roomRepository.save(room);
                deviceRepository.save(createdDevice);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getName());
            }
            case AlarmSystemDTO alarmSystemDTO -> {
                if (deviceRepository.findById("ALARM_DEVICE").isPresent()) {
                    throw new RuntimeException("Alarm system already exists");
                }
                if (alarmSystemDTO.getPassword() == null || alarmSystemDTO.getPassword().isEmpty()) {
                    throw new RuntimeException("Password cannot be null or empty for Alarm System");
                }
                createdDevice = new AlarmSystem(alarmSystemDTO.getPassword());
                deviceRepository.save(createdDevice);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getName());
            }
            case null, default -> throw new RuntimeException("Unsupported device type");
        }
    }

    public void removeDevice(String deviceId) {
        Room room = roomRepository.findById(deviceId.toUpperCase()).orElse(null);
        if (room != null) {
            room.getDeviceIds().remove(deviceId);
            roomRepository.save(room);
        }
        Device device = deviceRepository.findById(deviceId.toUpperCase()).orElseThrow(() -> new RuntimeException("Device not found"));
        deviceRepository.deleteById(device);

    }

    public DeviceResponseDTO getDevice(String deviceId) {
        Device device = deviceRepository.findById(deviceId.toUpperCase()).orElseThrow(() -> new RuntimeException("Device not found"));
        if (device instanceof SmartAssistant smartAssistant) {
            SmartAssistantResponseDTO responseDTO = new SmartAssistantResponseDTO();
            responseDTO.setId(smartAssistant.getId());
            responseDTO.setType(smartAssistant.getClass().getSimpleName());
            responseDTO.setNameOfAssistant(smartAssistant.getNameOfService());
            return responseDTO;
        } else if (device instanceof LightBulb lightBulb) {
            LightBulbResponseDTO responseDTO = new LightBulbResponseDTO();
            responseDTO.setId(lightBulb.getId());
            responseDTO.setType(lightBulb.getClass().getSimpleName());
            responseDTO.setIntensity(lightBulb.getIntensity());
            responseDTO.setRoomId(lightBulb.getRoomId());
            responseDTO.setOn(lightBulb.isOn());
            return responseDTO;
        } else if (device instanceof Thermostat thermostat) {
            ThermostatResponseDTO responseDTO = new ThermostatResponseDTO();
            responseDTO.setId(thermostat.getId());
            responseDTO.setType(thermostat.getClass().getSimpleName());
            responseDTO.setCurrentTemperature(thermostat.getTemperature());
            responseDTO.setRoomId(thermostat.getRoomId());
            return responseDTO;
        } else if (device instanceof AlarmSystem alarmSystem) {
            AlarmSystemResponseDTO responseDTO = new AlarmSystemResponseDTO();
            responseDTO.setId(alarmSystem.getId());
            responseDTO.setType(alarmSystem.getClass().getSimpleName());
            responseDTO.setArmed(alarmSystem.isArmed());
            return responseDTO;
        }
        throw new RuntimeException("Unsupported device type");
    }
}
