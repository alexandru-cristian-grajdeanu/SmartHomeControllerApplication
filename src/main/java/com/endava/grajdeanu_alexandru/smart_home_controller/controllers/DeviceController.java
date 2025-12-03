package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.inputDTOs.DeviceDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs.DeviceResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.DeviceService;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DeviceController {
    private final DeviceService deviceService;
    private final RoomService roomService;

    public DeviceController(DeviceService deviceService, RoomService roomService) {
        this.deviceService = deviceService;
        this.roomService = roomService;
    }

    @PostMapping("/devices")
    public ResponseEntity<DeviceResponseDTO> createDevice(@RequestBody DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.addDevice(deviceDTO));
    }

    @GetMapping("/devices")
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<DeviceResponseDTO> getDeviceById(@PathVariable String deviceId) {
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }

    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String deviceId) {
        deviceService.removeDevice(deviceId);
        return ResponseEntity.ok().build();
    }
}
