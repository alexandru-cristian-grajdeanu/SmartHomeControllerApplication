package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.Setter;

@Setter
public class AlarmStatusDTO {
    private String deviceId;
    private boolean armed;
}
