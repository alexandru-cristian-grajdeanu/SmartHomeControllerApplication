package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.lightbulbs;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import lombok.Getter;

@Getter
public class LightBulbDTO extends DeviceDTO {
    private String nameOfLightBulb;
    private String roomId;
    private int intensity;
}
