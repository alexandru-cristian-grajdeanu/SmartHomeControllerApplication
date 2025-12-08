package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DeviceAlreadyExistsException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.UnsupportedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.general_exceptions.InvalidValuesException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/devices")
    public ResponseEntity<DeviceResponseDTO> createDevice(@RequestBody DeviceDTO deviceDTO) throws NoRoomFoundException, InvalidValuesException, UnsupportedDeviceException, DeviceAlreadyExistsException {
        return ResponseEntity.ok(deviceService.addDevice(deviceDTO));
    }

    @GetMapping("/devices")
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<DeviceResponseDTO> getDeviceById(@PathVariable String deviceId) throws NoDeviceFoundException, UnsupportedDeviceException {
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }

    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String deviceId) throws NoDeviceFoundException {
        deviceService.removeDevice(deviceId);
        return ResponseEntity.ok().build();
    }
}
