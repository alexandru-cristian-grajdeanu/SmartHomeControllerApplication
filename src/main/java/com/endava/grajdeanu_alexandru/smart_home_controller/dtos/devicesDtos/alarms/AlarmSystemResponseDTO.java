package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.alarms;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmSystemResponseDTO extends DeviceResponseDTO {
    private boolean isArmed;

    public AlarmSystemResponseDTO(String id, String type, boolean isArmed) {
        super(id, type);
        this.isArmed = isArmed;
    }
}
