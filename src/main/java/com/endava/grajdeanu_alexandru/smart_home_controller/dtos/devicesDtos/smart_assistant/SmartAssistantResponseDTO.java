package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.smart_assistant;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartAssistantResponseDTO extends DeviceResponseDTO {
    private String nameOfAssistant;

    public SmartAssistantResponseDTO(String id, String type, String nameOfAssistant) {
        super(id, type);
        this.nameOfAssistant = nameOfAssistant;
    }
}
