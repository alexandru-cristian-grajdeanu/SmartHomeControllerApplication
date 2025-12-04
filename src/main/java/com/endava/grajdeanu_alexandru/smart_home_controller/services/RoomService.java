package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs.DeviceResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomInputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomOutputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.device_repositories.DeviceRepository;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;

    public RoomService(RoomRepository roomRepository, DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public RoomOutputDTO addRoom(RoomInputDTO room) {
        if (room.getNameOfRoom() == null || room.getNameOfRoom().isEmpty()) {
            throw new RuntimeException("Room name cannot be null or empty");
        }
        if (roomRepository.findById(room.getNameOfRoom().toUpperCase()).isPresent()) {
            throw new RuntimeException("Room with the same name already exists");
        }
        Room roomToAdd = new Room(room.getNameOfRoom());
        roomRepository.save(roomToAdd);
        RoomOutputDTO roomOutputDTO = new RoomOutputDTO();
        roomOutputDTO.setRoomName(roomToAdd.getIdRoom());
        roomOutputDTO.setDevices(new ArrayList<>());
        return roomOutputDTO;
    }

    public RoomOutputDTO getRoom(String roomId) {
        Room room = roomRepository.findById(roomId.toUpperCase()).orElseThrow(() -> new RuntimeException("Room not found"));
        RoomOutputDTO roomOutputDTO = new RoomOutputDTO();
        roomOutputDTO.setRoomName(room.getIdRoom());
        List<DeviceResponseDTO> deviceResponseDTOs = new ArrayList<>();
        room.getDeviceIds().forEach(deviceId -> {
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            DeviceResponseDTO deviceResponseDTO = new DeviceResponseDTO();
            deviceResponseDTO.setId(device.getId());
            deviceResponseDTO.setType(device.getClass().getSimpleName());
            deviceResponseDTOs.add(deviceResponseDTO);
        });
        roomOutputDTO.setDevices(deviceResponseDTOs);
        return roomOutputDTO;
    }

    public List<RoomOutputDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomOutputDTO> roomOutputDTOs = new ArrayList<>();
        for (Room room : rooms) {
            RoomOutputDTO roomOutputDTO = new RoomOutputDTO();
            roomOutputDTO.setRoomName(room.getIdRoom());
            List<DeviceResponseDTO> deviceResponseDTOs = new ArrayList<>();
            room.getDeviceIds().forEach(deviceId -> {
                var device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
                DeviceResponseDTO deviceResponseDTO = new DeviceResponseDTO();
                deviceResponseDTO.setId(device.getId());
                deviceResponseDTO.setType(device.getClass().getSimpleName());
                deviceResponseDTOs.add(deviceResponseDTO);
            });
            roomOutputDTO.setDevices(deviceResponseDTOs);
            roomOutputDTOs.add(roomOutputDTO);
        }
        return roomOutputDTOs;
    }

    public void removeRoom(String roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        for(String deviceId : room.getDeviceIds()) {
            Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            deviceRepository.deleteById(device);
        }
        roomRepository.deleteById(room);
    }
}
