package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.outputDTOs.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomOutputDTO {
    private String roomName;
    private List<DeviceResponseDTO> devices;
}
