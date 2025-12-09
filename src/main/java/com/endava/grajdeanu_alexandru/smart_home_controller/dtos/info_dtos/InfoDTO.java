package com.endava.grajdeanu_alexandru.smart_home_controller.dtos.info_dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoDTO {
    private String sessionId;
    private String applicationName;
    private String dataSource;
}
