package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightBulbResponseDTO extends DeviceResponseDTO {
    private int intensity;
    private boolean isOn;
    private String roomId;
}
