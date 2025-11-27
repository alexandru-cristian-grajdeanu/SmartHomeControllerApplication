package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import lombok.Getter;

@Getter
public abstract class RoomDevice extends Device {
    private final String roomId;

    public RoomDevice(String roomId, String name) {
        super(name);
        this.roomId = roomId;
    }
}
