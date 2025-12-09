package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

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
    public ResponseEntity<Map<String, String>> getInfo(){
        return ResponseEntity.ok(Map.of(
                "sessionId", sessionRequestTracker.getSessionId(),
                "applicationName", nameOfApp,
                "dataSource", dataSource));
    }

    @GetMapping("/session-count")
    public ResponseEntity<Map<String, String>> getCount(){
        return ResponseEntity.ok(Map.of("requestCount", String.valueOf(sessionRequestTracker.getRequestCount())));
    }
}
