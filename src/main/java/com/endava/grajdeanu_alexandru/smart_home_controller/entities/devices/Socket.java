package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Socket extends Device {
    private Room room;

    public Socket() {
        super();
    }
}
