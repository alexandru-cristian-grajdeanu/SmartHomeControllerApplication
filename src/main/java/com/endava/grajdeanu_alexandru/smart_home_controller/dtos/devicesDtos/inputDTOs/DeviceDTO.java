package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.inputDTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SmartHomeAssistantDTO.class, name = "smart_assistant"),
        @JsonSubTypes.Type(value = LightBulbDTO.class, name = "light_bulb"),
        @JsonSubTypes.Type(value = ThermostatDTO.class, name = "thermostat"),
        @JsonSubTypes.Type(value=AlarmSystemDTO.class, name="alarm_system")
})
@Getter
public class DeviceDTO {
    private String type;
}
