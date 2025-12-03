package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Getter;

@Getter
public class LightBulb extends RoomDevice {
    private final int intensity;
    private boolean isOn;
    public LightBulb(int intensity, String roomId, String name) {
        super(roomId, "LIGHT_BULB_" + name.toUpperCase() + "_" + roomId.toUpperCase());
        this.intensity = intensity;
        this.isOn = false;
    }

    public void switchOn() {
        this.isOn = true;
    }

    public void switchOff() {
        this.isOn = false;
    }
}
