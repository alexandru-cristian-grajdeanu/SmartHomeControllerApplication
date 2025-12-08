package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomInputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos.RoomOutputDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.general_exceptions.InvalidValuesException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.RoomAlreadyExistsException;
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

    public RoomOutputDTO addRoom(RoomInputDTO room) throws InvalidValuesException, RoomAlreadyExistsException {
        if (room.getNameOfRoom() == null || room.getNameOfRoom().isEmpty()) {
            throw new InvalidValuesException("Room name cannot be null or empty");
        }
        if (roomRepository.findById(room.getNameOfRoom().toUpperCase()).isPresent()) {
            throw new RoomAlreadyExistsException("Room with the same name already exists");
        }
        Room roomToAdd = new Room(room.getNameOfRoom());
        roomRepository.save(roomToAdd);
        return new RoomOutputDTO(roomToAdd.getIdRoom(), new ArrayList<>());
    }

    private List<DeviceResponseDTO> getDevicesForRoom(Room room) throws NoDeviceFoundException {
        List<DeviceResponseDTO> deviceResponseDTOs = new ArrayList<>();
        for(String deviceId : room.getDeviceIds()){
            var device = deviceRepository.findById(deviceId).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
            deviceResponseDTOs.add(new DeviceResponseDTO(device.getId(), device.getClass().getSimpleName()));
        }
        return deviceResponseDTOs;
    }

    public RoomOutputDTO getRoom(String roomId) throws NoRoomFoundException, NoDeviceFoundException {
        Room room = roomRepository.findById(roomId.toUpperCase()).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        return new RoomOutputDTO(room.getIdRoom(), getDevicesForRoom(room));
    }

    public List<RoomOutputDTO> getAllRooms() throws NoDeviceFoundException {
        List<Room> rooms = roomRepository.findAll();
        List<RoomOutputDTO> roomOutputDTOs = new ArrayList<>();
        for (Room room : rooms) {
            roomOutputDTOs.add(new RoomOutputDTO(room.getIdRoom(), getDevicesForRoom(room)));
        }
        return roomOutputDTOs;
    }

    public void removeRoom(String roomId) throws NoRoomFoundException, NoDeviceFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoRoomFoundException("Room not found"));
        for(String deviceId : room.getDeviceIds()) {
            Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new NoDeviceFoundException("Device not found"));
            deviceRepository.deleteById(device);
        }
        roomRepository.deleteById(room);
    }
}
