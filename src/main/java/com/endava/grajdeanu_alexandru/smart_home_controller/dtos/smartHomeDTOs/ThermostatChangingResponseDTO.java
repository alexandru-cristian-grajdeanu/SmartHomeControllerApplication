package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThermostatChangingResponseDTO {
    private String deviceId;
    private double newTemperature;
    private String roomId;
}
