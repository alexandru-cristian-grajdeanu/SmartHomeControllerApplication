package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.*;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories.DeviceRepository;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmartHomeService {
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    public SmartHomeService(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public AlarmStatusDTO armAlarmSystem(AlarmSystemCallingDTO alarmSystemCallingDTO) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(alarmSystemCallingDTO.getPassword())) {
                if (alarmSystem.isArmed()) {
                    throw new RuntimeException("Alarm system is already armed");
                }
                alarmSystem.armSystem();
                deviceRepository.save(alarmSystem);
                AlarmStatusDTO alarmStatusDTO = new AlarmStatusDTO();
                alarmStatusDTO.setDeviceId(alarmSystem.getId());
                alarmStatusDTO.setArmed(alarmSystem.isArmed());
                return alarmStatusDTO;
            } else {
                throw new RuntimeException("Incorrect password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public AlarmStatusDTO disarmAlarmSystem(AlarmSystemCallingDTO alarmSystemCallingDTO) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(alarmSystemCallingDTO.getPassword())) {
                if (!alarmSystem.isArmed()) {
                    throw new RuntimeException("Alarm system is already disarmed");
                }
                alarmSystem.disarmSystem();
                deviceRepository.save(alarmSystem);
                AlarmStatusDTO alarmStatusDTO = new AlarmStatusDTO();
                alarmStatusDTO.setDeviceId(alarmSystem.getId());
                alarmStatusDTO.setArmed(alarmSystem.isArmed());
                return alarmStatusDTO;
            } else {
                throw new RuntimeException("Incorrect password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public RoomDevicesStatusDTO turnOffDevicesInRoom(RoomCallingDTO roomCallingDTO) {
        var room = roomRepository.findById(roomCallingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        List<DeviceStatusDTO> deviceStatusDTOs = new ArrayList<>();
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            device.turnOff();
            deviceRepository.save(device);
            DeviceStatusDTO deviceStatusDTO = new DeviceStatusDTO();
            deviceStatusDTO.setDeviceName(device.getId());
            deviceStatusDTO.setConnectedToPower(device.isActive());
            deviceStatusDTOs.add(deviceStatusDTO);
        });
        RoomDevicesStatusDTO roomDevicesStatusDTO = new RoomDevicesStatusDTO();
        roomDevicesStatusDTO.setRoomName(room.getIdRoom());
        roomDevicesStatusDTO.setDevices(deviceStatusDTOs);
        return roomDevicesStatusDTO;
    }

    public RoomDevicesStatusDTO turnOnDevicesInRoom(RoomCallingDTO roomCallingDTO) {
        var room = roomRepository.findById(roomCallingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        List<DeviceStatusDTO> deviceStatusDTOs = new ArrayList<>();
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            device.turnOn();
            deviceRepository.save(device);
            DeviceStatusDTO deviceStatusDTO = new DeviceStatusDTO();
            deviceStatusDTO.setDeviceName(device.getId());
            deviceStatusDTO.setConnectedToPower(device.isActive());
            deviceStatusDTOs.add(deviceStatusDTO);
        });
        RoomDevicesStatusDTO roomDevicesStatusDTO = new RoomDevicesStatusDTO();
        roomDevicesStatusDTO.setRoomName(room.getIdRoom());
        roomDevicesStatusDTO.setDevices(deviceStatusDTOs);
        return roomDevicesStatusDTO;
    }

    public ResponseChangePasswordDTO changeAlarmPassword(ChangePasswordDTO changePasswordDTO) {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new RuntimeException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(changePasswordDTO.getOldPassword())) {
                alarmSystem.changePassword(changePasswordDTO.getNewPassword());
                deviceRepository.save(alarmSystem);
                ResponseChangePasswordDTO responseChangePasswordDTO = new ResponseChangePasswordDTO();
                responseChangePasswordDTO.setDeviceId(alarmDevice.getId());
                responseChangePasswordDTO.setPasswordChanged(true);
                return responseChangePasswordDTO;
            } else {
                throw new RuntimeException("Incorrect old password");
            }
        }else{
            throw new RuntimeException("Device is not an Alarm System");
        }
    }

    public ThermostatChangingResponseDTO changeTemperatureInRoom(ThermostatCallingDTO thermostatCallingDTO) {
        var room = roomRepository.findById(thermostatCallingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        var thermostatId = room.getDeviceIds().stream().filter(id -> id.startsWith("THERMOSTAT_")).findFirst()
                .orElseThrow(() -> new RuntimeException("Thermostat not found in room"));
        var thermostat = deviceRepository.findById(thermostatId).orElseThrow(() -> new RuntimeException("Thermostat device not found"));
        if (thermostat instanceof Thermostat thermostatDevice) {
            if (thermostatDevice.isActive())
            {
                thermostatDevice.setTemperature(thermostatCallingDTO.getNewTemperature());
                deviceRepository.save(thermostatDevice);
                ThermostatChangingResponseDTO thermostatResponseDTO = new ThermostatChangingResponseDTO();
                thermostatResponseDTO.setDeviceId(thermostatDevice.getId());
                thermostatResponseDTO.setRoomId(thermostatDevice.getRoomId());
                thermostatResponseDTO.setNewTemperature(thermostatDevice.getTemperature());
                return thermostatResponseDTO;
            }else{
                throw new RuntimeException("Thermostat is not active");
            }
        }else{
            throw new RuntimeException("Device is not a Thermostat");
        }

    }

    public LightBulbsStatusDTO closeLightsInRoom(RoomCallingDTO roomCallingDTO) {
        var room = roomRepository.findById(roomCallingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        List<LightBulbStatusDTO> lightBulbStatusDTOs = new ArrayList<>();
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            if(device instanceof LightBulb lightBulbDevice){
                lightBulbDevice.switchOff();
                deviceRepository.save(lightBulbDevice);
                LightBulbStatusDTO lightBulbStatusDTO = new LightBulbStatusDTO();
                lightBulbStatusDTO.setDeviceId(lightBulbDevice.getId());
                lightBulbStatusDTO.setOn(lightBulbDevice.isOn());
                lightBulbStatusDTOs.add(lightBulbStatusDTO);
            }
        }
        );
        LightBulbsStatusDTO lightBulbsStatusDTO = new LightBulbsStatusDTO();
        lightBulbsStatusDTO.setRoomId(room.getIdRoom());
        lightBulbsStatusDTO.setDevices(lightBulbStatusDTOs);
        return lightBulbsStatusDTO;
    }

    public LightBulbsStatusDTO openLightsInRoom(RoomCallingDTO roomCallingDTO) {
        var room = roomRepository.findById(roomCallingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        List<LightBulbStatusDTO> lightBulbStatusDTOs = new ArrayList<>();
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            if(device instanceof LightBulb lightBulbDevice) {
                lightBulbDevice.switchOn();
                deviceRepository.save(lightBulbDevice);
                LightBulbStatusDTO lightBulbStatusDTO = new LightBulbStatusDTO();
                lightBulbStatusDTO.setDeviceId(lightBulbDevice.getId());
                lightBulbStatusDTO.setOn(lightBulbDevice.isOn());
                lightBulbStatusDTOs.add(lightBulbStatusDTO);
            }
        });
        LightBulbsStatusDTO lightBulbsStatusDTO = new LightBulbsStatusDTO();
        lightBulbsStatusDTO.setRoomId(room.getIdRoom());
        lightBulbsStatusDTO.setDevices(lightBulbStatusDTOs);
        return lightBulbsStatusDTO;

    }

    public MessageFromSmartAssistantDTO callAssistant() {
        var device = deviceRepository.findAll().stream().filter(d -> d.getId().startsWith("SMART_ASSISTANT")).findFirst()
                .orElseThrow(() -> new RuntimeException("Smart Assistant device not found"));
        if(device instanceof SmartAssistant smartAssistant){
            MessageFromSmartAssistantDTO messageFromSmartAssistantDTO = new MessageFromSmartAssistantDTO();
            messageFromSmartAssistantDTO.setMessage(smartAssistant.helloService());
            System.out.println(messageFromSmartAssistantDTO.getMessage());
            return messageFromSmartAssistantDTO;
        }
        throw new RuntimeException("Device is not a Smart Assistant");
    }
}
