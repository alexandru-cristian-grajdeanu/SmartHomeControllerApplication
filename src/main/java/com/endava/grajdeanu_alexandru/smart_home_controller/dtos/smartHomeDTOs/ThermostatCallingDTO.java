package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.Getter;

@Getter
public class ThermostatCallingDTO {
    private double newTemperature;
    private String deviceId;
    private String roomId;
}
