package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceStatusDTO {
    private String deviceName;
    private boolean connectedToPower;
}
