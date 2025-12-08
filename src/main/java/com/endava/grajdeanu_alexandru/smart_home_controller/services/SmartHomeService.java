package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.*;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.alarms.AlarmStatusDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.alarms.AlarmSystemCallingDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.lightbulbs.LightBulbStatusDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.lightbulbs.LightBulbsStatusDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats.ThermostatCallingDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats.ThermostatChangingResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DisconnectedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyArmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyDisarmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.UnsupportedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.security_exceptions.IncorrectPasswordException;
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

    public AlarmStatusDTO armAlarmSystem(AlarmSystemCallingDTO alarmSystemCallingDTO) throws NoDeviceFoundException, AlreadyArmedAlarmException, IncorrectPasswordException, UnsupportedDeviceException {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new NoDeviceFoundException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(alarmSystemCallingDTO.getPassword())) {
                if (alarmSystem.isArmed()) {
                    throw new AlreadyArmedAlarmException("Alarm system is already armed");
                }
                alarmSystem.armSystem();
                deviceRepository.save(alarmSystem);
                return new AlarmStatusDTO(alarmDevice.getId(), alarmSystem.isArmed());
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        }else{
            throw new UnsupportedDeviceException("Device is not an Alarm System");
        }
    }

    public AlarmStatusDTO disarmAlarmSystem(AlarmSystemCallingDTO alarmSystemCallingDTO) throws NoDeviceFoundException, AlreadyDisarmedAlarmException, IncorrectPasswordException, UnsupportedDeviceException {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new NoDeviceFoundException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(alarmSystemCallingDTO.getPassword())) {
                if (!alarmSystem.isArmed()) {
                    throw new AlreadyDisarmedAlarmException("Alarm system is already disarmed");
                }
                alarmSystem.disarmSystem();
                deviceRepository.save(alarmSystem);
                return new AlarmStatusDTO(alarmDevice.getId(), alarmSystem.isArmed());
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        }else{
            throw new UnsupportedDeviceException("Device is not an Alarm System");
        }
    }

    private List<DeviceStatusDTO> getDevicesStatusInRoom(Room room) throws NoDeviceFoundException {
        List<DeviceStatusDTO> deviceStatusDTOs = new ArrayList<>();
        for(String deviceId : room.getDeviceIds()){
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
            deviceStatusDTOs.add(new DeviceStatusDTO(device.getId(), device.isActive()));
        }
        return deviceStatusDTOs;
    }

    public RoomDevicesStatusDTO turnOffDevicesInRoom(RoomCallingDTO roomCallingDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var room = roomRepository.findById(roomCallingDTO.getRoomId().toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        return new RoomDevicesStatusDTO(room.getIdRoom(), getDevicesStatusInRoom(room));
    }

    public RoomDevicesStatusDTO turnOnDevicesInRoom(RoomCallingDTO roomCallingDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var room = roomRepository.findById(roomCallingDTO.getRoomId().toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        return new RoomDevicesStatusDTO(room.getIdRoom(), getDevicesStatusInRoom(room));
    }

    public ResponseChangePasswordDTO changeAlarmPassword(ChangePasswordDTO changePasswordDTO) throws NoDeviceFoundException, IncorrectPasswordException, UnsupportedDeviceException {
        Device alarmDevice = deviceRepository.findById("ALARM_DEVICE").orElseThrow(() -> new NoDeviceFoundException("Alarm device not found"));
        if (alarmDevice instanceof AlarmSystem alarmSystem){
            if (alarmSystem.getPassword().equals(changePasswordDTO.getOldPassword())) {
                alarmSystem.changePassword(changePasswordDTO.getNewPassword());
                deviceRepository.save(alarmSystem);
                return new ResponseChangePasswordDTO(alarmDevice.getId(), true);
            } else {
                throw new IncorrectPasswordException("Incorrect old password");
            }
        }else{
            throw new UnsupportedDeviceException("Device is not an Alarm System");
        }
    }

    public ThermostatChangingResponseDTO changeTemperatureInRoom(ThermostatCallingDTO thermostatCallingDTO) throws NoDeviceFoundException, NoRoomFoundException, DisconnectedDeviceException, UnsupportedDeviceException {
        var room = roomRepository.findById(thermostatCallingDTO.getRoomId().toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        var thermostatId = room.getDeviceIds().stream().filter(id -> id.startsWith("THERMOSTAT_")).findFirst()
                .orElseThrow(() -> new NoDeviceFoundException("Thermostat not found in room"));
        var thermostat = deviceRepository.findById(thermostatId).orElseThrow(() -> new NoDeviceFoundException("Thermostat device not found"));
        if (thermostat instanceof Thermostat thermostatDevice) {
            if (thermostatDevice.isActive())
            {
                thermostatDevice.setTemperature(thermostatCallingDTO.getNewTemperature());
                deviceRepository.save(thermostatDevice);
                return new ThermostatChangingResponseDTO(thermostatDevice.getId(), thermostatDevice.getTemperature(), thermostatDevice.getRoomId());
            }else{
                throw new DisconnectedDeviceException("Thermostat is not active");
            }
        }else{
            throw new UnsupportedDeviceException("Device is not a Thermostat");
        }

    }

    public LightBulbsStatusDTO closeLightsInRoom(RoomCallingDTO roomCallingDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var room = roomRepository.findById(roomCallingDTO.getRoomId().toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        List<LightBulbStatusDTO> lightBulbStatusDTOs = new ArrayList<>();
        for(String deviceId : room.getDeviceIds()){
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
            if(device instanceof LightBulb lightBulbDevice){
                lightBulbDevice.switchOff();
                deviceRepository.save(lightBulbDevice);
                lightBulbStatusDTOs.add(new LightBulbStatusDTO(lightBulbDevice.getId(), lightBulbDevice.isOn()));
            }
        }
        return new LightBulbsStatusDTO(room.getIdRoom(), lightBulbStatusDTOs);
    }

    public LightBulbsStatusDTO openLightsInRoom(RoomCallingDTO roomCallingDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var room = roomRepository.findById(roomCallingDTO.getRoomId().toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        List<LightBulbStatusDTO> lightBulbStatusDTOs = new ArrayList<>();
        for(String deviceId : room.getDeviceIds()){
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
            if(device instanceof LightBulb lightBulbDevice) {
                lightBulbDevice.switchOn();
                deviceRepository.save(lightBulbDevice);
                lightBulbStatusDTOs.add(new LightBulbStatusDTO(lightBulbDevice.getId(), lightBulbDevice.isOn()));
            }
        }
        return new LightBulbsStatusDTO(room.getIdRoom(), lightBulbStatusDTOs);
    }

    public MessageFromSmartAssistantDTO callAssistant() throws NoDeviceFoundException, UnsupportedDeviceException {
        var device = deviceRepository.findAll().stream().filter(d -> d.getId().startsWith("SMART_ASSISTANT")).findFirst()
                .orElseThrow(() -> new NoDeviceFoundException("Smart Assistant device not found"));
        if(device instanceof SmartAssistant smartAssistant){
            return new MessageFromSmartAssistantDTO(smartAssistant.helloService());
        }
        throw new UnsupportedDeviceException("Device is not a Smart Assistant");
    }
}
