package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.roomsDtos;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.devicesDtos.devices.DeviceResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class RoomOutputDTO {
    private String roomName;
    private List<DeviceResponseDTO> devices;

    public RoomOutputDTO(String roomName, List<DeviceResponseDTO> devices) {
        this.roomName = roomName;
        this.devices = devices;
    }
}
