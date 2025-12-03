package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmSystemResponseDTO extends DeviceResponseDTO{
    private boolean isArmed;
}
