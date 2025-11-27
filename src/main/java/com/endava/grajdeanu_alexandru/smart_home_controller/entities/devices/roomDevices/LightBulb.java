package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Getter;

@Getter
public class LightBulb extends RoomDevice {
    private final int intensity;
    public LightBulb(int intensity, Room room) {
        super(room.getIdRoom(), "LIGHT_BULB_" + room.getIdRoom().toUpperCase());
        this.intensity = intensity;
    }
}
