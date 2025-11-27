package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

public class SmartAssistant extends Device {
    private final String nameOfService;

    public SmartAssistant(String nameOfService) {
        super("SMART_ASSISTANT_" + nameOfService.toUpperCase());
        this.nameOfService = nameOfService;
    }

    public String helloService() {
        return "Hello, I am your smart assistent: " + nameOfService;
    }
}
