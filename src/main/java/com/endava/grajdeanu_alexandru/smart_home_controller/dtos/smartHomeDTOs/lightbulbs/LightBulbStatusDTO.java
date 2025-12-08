package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.lightbulbs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LightBulbStatusDTO {
    private String deviceId;
    private boolean isOn;
}
