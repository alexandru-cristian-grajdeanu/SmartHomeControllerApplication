package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.lightbulbs;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightBulbResponseDTO extends DeviceResponseDTO {
    private int intensity;
    private boolean isOn;
    private String roomId;

    public LightBulbResponseDTO(String id, String type, int intensity, boolean isOn, String roomId) {
        super(id, type);
        this.intensity = intensity;
        this.isOn = isOn;
        this.roomId = roomId;
    }
}
