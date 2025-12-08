package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.*;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.alarms.AlarmStatusDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.alarms.AlarmSystemCallingDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.lightbulbs.LightBulbsStatusDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats.ThermostatCallingDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats.ThermostatChangingResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyArmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyDisarmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DisconnectedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.UnsupportedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.security_exceptions.IncorrectPasswordException;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.SmartHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/smart-home")
public class SmartHomeController {
    private final SmartHomeService smartHomeService;

    public SmartHomeController(SmartHomeService smartHomeService) {
        this.smartHomeService = smartHomeService;
    }

    @GetMapping("/call-assistant")
    public ResponseEntity<MessageFromSmartAssistantDTO> callAssistant() throws NoDeviceFoundException, UnsupportedDeviceException {
        var response = smartHomeService.callAssistant();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/activate-alarm-system")
    public ResponseEntity<AlarmStatusDTO> activateAlarmSystem(
            @RequestBody AlarmSystemCallingDTO alarmSystemCallingDTO
    ) throws NoDeviceFoundException, IncorrectPasswordException, UnsupportedDeviceException, AlreadyArmedAlarmException {
        var response = smartHomeService.armAlarmSystem(alarmSystemCallingDTO);
        System.out.println(response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deactivate-alarm-system")
    public ResponseEntity<AlarmStatusDTO> deactivateAlarmSystem(
            @RequestBody AlarmSystemCallingDTO alarmSystemCallingDTO
    ) throws NoDeviceFoundException, IncorrectPasswordException, UnsupportedDeviceException, AlreadyDisarmedAlarmException {
        var response = smartHomeService.disarmAlarmSystem(alarmSystemCallingDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseChangePasswordDTO> changeAlarmSystemPassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws NoDeviceFoundException, IncorrectPasswordException, UnsupportedDeviceException {
        var response = smartHomeService.changeAlarmPassword(changePasswordDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/disconnect-devices-room")
    public ResponseEntity<RoomDevicesStatusDTO> disconnectAllDevicesInRoom(@RequestBody RoomCallingDTO roomNameDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var response = smartHomeService.turnOffDevicesInRoom(roomNameDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/connect-devices-room")
    public ResponseEntity<RoomDevicesStatusDTO> connectAllDevicesInRoom(@RequestBody RoomCallingDTO roomNameDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var response = smartHomeService.turnOnDevicesInRoom(roomNameDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/close-the-lights-room")
    public ResponseEntity<LightBulbsStatusDTO> turnOffLightsInRoom(@RequestBody RoomCallingDTO roomNameDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var response = smartHomeService.closeLightsInRoom(roomNameDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/open-the-lights-room")
    public ResponseEntity<LightBulbsStatusDTO> turnOnLightsInRoom(@RequestBody RoomCallingDTO roomNameDTO) throws NoRoomFoundException, NoDeviceFoundException {
        var response = smartHomeService.openLightsInRoom(roomNameDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/set-temperature-room")
    public ResponseEntity<ThermostatChangingResponseDTO> setTemperatureInRoom(@RequestBody ThermostatCallingDTO thermostatCallingDTO) throws NoRoomFoundException, NoDeviceFoundException, UnsupportedDeviceException, DisconnectedDeviceException {
        var response = smartHomeService.changeTemperatureInRoom(thermostatCallingDTO);
        return ResponseEntity.ok(response);
    }
}
