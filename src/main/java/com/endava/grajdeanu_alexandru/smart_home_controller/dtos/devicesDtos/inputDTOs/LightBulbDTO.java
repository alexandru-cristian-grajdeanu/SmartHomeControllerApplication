package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.inputDTOs;

import lombok.Getter;

@Getter
public class LightBulbDTO extends DeviceDTO {
    private String nameOfLightBulb;
    private String roomId;
    private int intensity;
}
