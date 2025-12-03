package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.smartHomeDTOs.MessageFromSmartAssistantDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.SmartHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/smart-home")
public class SmartHomeController {
    private final SmartHomeService smartHomeService;

    public SmartHomeController(SmartHomeService smartHomeService) {
        this.smartHomeService = smartHomeService;
    }

    @GetMapping("/call-assistant")
    public ResponseEntity<MessageFromSmartAssistantDTO> callAssistant(){
        var response = smartHomeService.callAssistant();
        return ResponseEntity.ok(response);
    }


}
