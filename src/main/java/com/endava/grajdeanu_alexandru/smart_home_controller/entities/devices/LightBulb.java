package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LightBulb extends Device {
    private int intensity;
    private Room room;

    public LightBulb(int intensity) {
        super();
        this.intensity = intensity;
    }
}
