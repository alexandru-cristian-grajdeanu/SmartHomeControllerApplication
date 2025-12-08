package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.alarms.AlarmSystemDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.alarms.AlarmSystemResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.lightbulbs.LightBulbDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.lightbulbs.LightBulbResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.smart_assistant.SmartAssistantResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.smart_assistant.SmartHomeAssistantDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.thermostats.ThermostatDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.thermostats.ThermostatResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DeviceAlreadyExistsException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.UnsupportedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.general_exceptions.InvalidValuesException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
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

    public DeviceResponseDTO addDevice(DeviceDTO device) throws NoRoomFoundException, DeviceAlreadyExistsException, InvalidValuesException, UnsupportedDeviceException {
        Device createdDevice;
        switch (device) {
            case SmartHomeAssistantDTO smartHomeAssistantDTO -> {
                createdDevice = new SmartAssistant(smartHomeAssistantDTO.getNameOfAssistant());
                deviceRepository.save(createdDevice);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getSimpleName());
            }
            case LightBulbDTO lightBulbDTO -> {
                if (lightBulbDTO.getRoomId() == null || roomRepository.findById(lightBulbDTO.getRoomId()).isEmpty()) {
                    throw new NoRoomFoundException("Room not found for LightBulb");
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
                    throw new NoRoomFoundException("Room not found for Thermostat");
                }
                if (deviceRepository.findAll().stream().filter(d -> d instanceof Thermostat).anyMatch(d -> {
                    Thermostat t = (Thermostat) d;
                    return t.getRoomId().equals(thermostatDTO.getRoomId());
                })) {
                    throw new DeviceAlreadyExistsException("Thermostat already exists in this room");
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
                    throw new DeviceAlreadyExistsException("Alarm system already exists");
                }
                if (alarmSystemDTO.getPassword() == null || alarmSystemDTO.getPassword().isEmpty()) {
                    throw new InvalidValuesException("Password cannot be null or empty for Alarm System");
                }
                createdDevice = new AlarmSystem(alarmSystemDTO.getPassword());
                deviceRepository.save(createdDevice);
                return new DeviceResponseDTO(createdDevice.getId(), createdDevice.getClass().getName());
            }
            case null, default -> throw new UnsupportedDeviceException("Unsupported device type");
        }
    }

    public void removeDevice(String deviceId) throws NoDeviceFoundException {
        Room room = roomRepository.findById(deviceId.toUpperCase()).orElse(null);
        if (room != null) {
            room.getDeviceIds().remove(deviceId);
            roomRepository.save(room);
        }
        Device device = deviceRepository.findById(deviceId.toUpperCase()).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
        deviceRepository.deleteById(device);

    }

    public DeviceResponseDTO getDevice(String deviceId) throws NoDeviceFoundException, UnsupportedDeviceException {
        Device device = deviceRepository.findById(deviceId.toUpperCase()).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
        switch (device) {
            case SmartAssistant smartAssistant -> {
                return new SmartAssistantResponseDTO(smartAssistant.getId(), smartAssistant.getClass().getSimpleName(), smartAssistant.getNameOfService());
            }
            case LightBulb lightBulb -> {
                return new LightBulbResponseDTO(lightBulb.getId(), lightBulb.getClass().getSimpleName(), lightBulb.getIntensity(), lightBulb.isOn(), lightBulb.getRoomId());
            }
            case Thermostat thermostat -> {
                return new ThermostatResponseDTO(thermostat.getId(), thermostat.getClass().getSimpleName(), thermostat.getTemperature(), thermostat.getRoomId());
            }
            case AlarmSystem alarmSystem -> {
                return new AlarmSystemResponseDTO(alarmSystem.getId(), alarmSystem.getClass().getSimpleName(), alarmSystem.isArmed());
            }
            case null, default -> throw new UnsupportedDeviceException("Unsupported device type");
        }
    }
}
