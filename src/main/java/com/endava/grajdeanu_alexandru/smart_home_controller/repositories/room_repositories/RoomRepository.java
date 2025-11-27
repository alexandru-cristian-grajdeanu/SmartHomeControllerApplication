package com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);
    List<Room> findAll();
    void deleteById(Room room);
    Optional<Room> findById(String id);
}
