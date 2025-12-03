package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SmartAssistantResponseDTO.class, name = "smart_assistant"),
        @JsonSubTypes.Type(value = LightBulbResponseDTO.class,          name = "light_bulb"),
        @JsonSubTypes.Type(value = ThermostatResponseDTO.class,         name = "thermostat")
})

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponseDTO {
    private String id;
    private String type;
}
