package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SmartAssistent extends Device {
    String nameOfService;

    public SmartAssistent() {
        super();
    }
}
