package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ThermostatChangingResponseDTO {
    private String deviceId;
    private double newTemperature;
    private String roomId;

}
