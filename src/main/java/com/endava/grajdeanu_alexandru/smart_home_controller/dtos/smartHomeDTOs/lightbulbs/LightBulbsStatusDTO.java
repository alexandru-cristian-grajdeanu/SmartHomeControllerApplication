package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.lightbulbs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class LightBulbsStatusDTO {
    private String roomId;
    private List<LightBulbStatusDTO> devices;
}
