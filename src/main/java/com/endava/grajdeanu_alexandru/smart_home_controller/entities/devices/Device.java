package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.Data;

@Data
public class Device {
    private boolean isActive;

    public Device(){
        this.isActive = false;
    }
}
