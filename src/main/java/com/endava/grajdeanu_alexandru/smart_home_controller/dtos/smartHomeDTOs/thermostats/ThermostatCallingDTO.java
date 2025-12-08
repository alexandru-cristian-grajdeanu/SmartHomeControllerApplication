package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.thermostats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThermostatCallingDTO {
    private double newTemperature;
    private String roomId;
}
