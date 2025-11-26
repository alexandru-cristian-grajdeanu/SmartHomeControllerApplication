package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlarmSystem extends Device {
    private String name;

    @Value("${spring.application.password}")
    private String password;

    public AlarmSystem() {
        super();
    }

}
