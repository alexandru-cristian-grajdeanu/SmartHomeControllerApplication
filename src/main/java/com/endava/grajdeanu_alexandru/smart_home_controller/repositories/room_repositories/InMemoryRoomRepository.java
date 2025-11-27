package com.endava.grajdeanu_alexandru.smart_home_controller.repositories.room_repositories;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRoomRepository implements  RoomRepository {
    private final Map<String, Room> rooms = new HashMap<>();

    @Override
    public void save(Room room) {
        rooms.put(room.getIdRoom(), room);
    }

    @Override
    public List<Room> findAll() {
        return List.copyOf(rooms.values());
    }

    @Override
    public void deleteById(Room room) {
        rooms.remove(room.getIdRoom());
    }

    @Override
    public Optional<Room> findById(String id) {
        return Optional.ofNullable(rooms.get(id));
    }

}
