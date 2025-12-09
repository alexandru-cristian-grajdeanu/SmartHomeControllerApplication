package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomInputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomOutputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.general_exceptions.InvalidValuesException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.RoomAlreadyExistsException;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.DeviceService;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.RoomService;
import com.endava.grajdeanu_alexandru.smart_home_controller.session.SessionRequestTracker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {
    private final RoomService roomService;
    private final SessionRequestTracker sessionRequestTracker;

    public RoomController(RoomService roomService, SessionRequestTracker sessionRequestTracker) {
        this.sessionRequestTracker = sessionRequestTracker;
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomOutputDTO> createRoom(@RequestBody RoomInputDTO roomInputDTO) throws InvalidValuesException, RoomAlreadyExistsException {
        sessionRequestTracker.incrementRequestCount();
        return ResponseEntity.ok(roomService.addRoom(roomInputDTO));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomOutputDTO> getRoomById(@PathVariable String roomId) throws NoRoomFoundException, NoDeviceFoundException {
        sessionRequestTracker.incrementRequestCount();
        return ResponseEntity.ok(roomService.getRoom(roomId));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomOutputDTO>> getAllRooms() throws NoDeviceFoundException {
        sessionRequestTracker.incrementRequestCount();
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @DeleteMapping("/rooms/{roomId}" )
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomId) throws NoRoomFoundException, NoDeviceFoundException {
        sessionRequestTracker.incrementRequestCount();
        roomService.removeRoom(roomId);
        return ResponseEntity.ok().build();
    }
}
