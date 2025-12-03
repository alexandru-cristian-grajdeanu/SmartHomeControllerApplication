package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomInputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomOutputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.DeviceService;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {
    private final RoomService roomService;
    private final DeviceService deviceService;

    public RoomController(RoomService roomService, DeviceService deviceService) {
        this.roomService = roomService;
        this.deviceService = deviceService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomOutputDTO> createRoom(@RequestBody RoomInputDTO roomInputDTO) {
        return ResponseEntity.ok(roomService.addRoom(roomInputDTO));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomOutputDTO> getRoomById(@PathVariable String roomId) {
        return ResponseEntity.ok(roomService.getRoom(roomId));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomOutputDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @DeleteMapping("/rooms/{roomId}" )
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomId) {
        roomService.removeRoom(roomId);
        return ResponseEntity.ok().build();
    }
}
