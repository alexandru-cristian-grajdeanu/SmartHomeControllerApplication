package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.alarms;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AlarmStatusDTO {
    private String deviceId;
    private boolean armed;
}
