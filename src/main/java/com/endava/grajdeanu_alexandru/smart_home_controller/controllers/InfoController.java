package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.info_dtos.CountInfoDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.info_dtos.InfoDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.session.SessionRequestTracker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/sessions")
public class InfoController {
    private final SessionRequestTracker sessionRequestTracker;
    @Value("${spring.application.name}")
    private String nameOfApp;

    @Value("${spring.application.datasource}")
    private String dataSource;

    public InfoController(SessionRequestTracker sessionRequestTracker) {
        this.sessionRequestTracker = sessionRequestTracker;
    }

    @GetMapping("/info")
    public ResponseEntity<InfoDTO> getInfo(){
        return ResponseEntity.ok(new InfoDTO(sessionRequestTracker.getSessionId(), nameOfApp, dataSource));
    }

    @GetMapping("/session-count")
    public ResponseEntity<CountInfoDTO> getCount(){
        return ResponseEntity.ok(new CountInfoDTO(sessionRequestTracker.getRequestCount()));
    }
}
