package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ResponseChangePasswordDTO {
    private String deviceId;
    private boolean passwordChanged;
}
