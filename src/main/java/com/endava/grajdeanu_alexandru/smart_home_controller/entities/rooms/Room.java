package com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms;


import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
public class Room {
    private final String idRoom;
    private final Set<String> deviceIds = new HashSet<>();

    public Room(String idRoom) {
        this.idRoom = idRoom;
    }

    public void addDeviceId(String deviceId) {
        deviceIds.add(deviceId);
    }

    public void removeDeviceId(String deviceId) {
        deviceIds.remove(deviceId);
    }
}
