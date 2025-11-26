package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Thermostat extends Device {
    private double temperature;
    private Room room;

    public Thermostat() {
        super();
    }
}
