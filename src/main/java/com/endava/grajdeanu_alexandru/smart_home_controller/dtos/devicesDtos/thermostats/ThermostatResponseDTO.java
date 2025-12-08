package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.thermostats;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThermostatResponseDTO extends DeviceResponseDTO {
    private double currentTemperature;
    private String roomId;

    public ThermostatResponseDTO(String id, String type, double currentTemperature, String roomId) {
        super(id, type);
        this.currentTemperature = currentTemperature;
        this.roomId = roomId;
    }

}
