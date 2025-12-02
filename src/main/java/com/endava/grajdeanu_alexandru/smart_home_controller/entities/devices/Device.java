package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Device {
    private String id;
    private boolean isActive;

    protected Device(String id) {
        this.isActive = true;
        this.id = id;
    }

    public void turnOn() {
        this.isActive = true;
    }

    public void turnOff() {
        this.isActive = false;
    }
}
