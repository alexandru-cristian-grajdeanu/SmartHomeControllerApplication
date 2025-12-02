package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Getter;

@Getter
public class LightBulb extends RoomDevice {
    private final int intensity;
    public LightBulb(int intensity, String roomId) {
        super(roomId, "LIGHT_BULB_" + roomId.toUpperCase());
        this.intensity = intensity;
    }
}
