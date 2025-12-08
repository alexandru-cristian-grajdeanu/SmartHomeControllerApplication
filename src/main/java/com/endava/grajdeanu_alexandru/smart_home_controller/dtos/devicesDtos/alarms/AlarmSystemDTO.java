package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.alarms;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import lombok.Getter;

@Getter
public class AlarmSystemDTO extends DeviceDTO {
    private String password;
}
