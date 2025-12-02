package com.endava.grajdeanu_alexandru.smart_home_controller.services;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public Room getRoom(String roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void removeRoom(Room room) {
        roomRepository.deleteById(room);
    }
}
