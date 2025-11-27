package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.Device;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thermostat extends RoomDevice {
    private double temperature;

    public Thermostat(Room room, double temperature) {
        super(room.getIdRoom(), "THERMOSTAT_" + room.getIdRoom().toUpperCase());
        this.temperature = temperature;
    }
}
