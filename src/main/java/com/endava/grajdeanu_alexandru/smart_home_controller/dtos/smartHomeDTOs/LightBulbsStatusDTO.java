package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LightBulbsStatusDTO {
    private String roomId;
    private List<LightBulbStatusDTO> devices;
}
