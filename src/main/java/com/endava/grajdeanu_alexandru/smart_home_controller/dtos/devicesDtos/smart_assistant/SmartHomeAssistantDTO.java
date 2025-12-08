package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.smart_assistant;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceDTO;
import lombok.Getter;

@Getter
public class SmartHomeAssistantDTO extends DeviceDTO {
    private String nameOfAssistant;
}
