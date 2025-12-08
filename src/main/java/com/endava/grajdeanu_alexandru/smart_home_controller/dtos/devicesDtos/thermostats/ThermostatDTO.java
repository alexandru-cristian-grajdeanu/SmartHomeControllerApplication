package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.thermostats;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import lombok.Getter;

@Getter
public class ThermostatDTO extends DeviceDTO {
    private String roomId;
}
