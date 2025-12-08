package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class RoomDevicesStatusDTO {
    private String roomName;
    private List<DeviceStatusDTO> devices;
}
