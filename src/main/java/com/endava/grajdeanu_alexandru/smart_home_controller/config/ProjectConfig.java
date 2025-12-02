package com.endava.grajdeanu_alexandru.smart_home_controller.config;

import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.endava.grajdeanu_alexandru.smart_home_controller"})
@EnableAspectJAutoProxy
public class ProjectConfig {
    @Bean
    public AlarmSystem alarmSystem() {
        return new AlarmSystem("12341234");
    }

    @Bean
    public SmartAssistant smartAssistant() {
        return new SmartAssistant("Jarvis");
    }
}
