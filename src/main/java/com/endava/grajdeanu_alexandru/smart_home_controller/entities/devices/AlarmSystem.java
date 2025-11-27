package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@EqualsAndHashCode(callSuper = true)
@Getter
public class AlarmSystem extends Device {
    private boolean isArmed;
    @Value("${spring.application.password}")
    private String password;

    public AlarmSystem() {
        super("ALARM_SYSTEM");
        this.isArmed = false;
    }

    public void armSystem(){
        this.isArmed = true;
    }

    public void disarmSystem(){
        this.isArmed = false;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void deactivate(String password) {
        if (this.password.equals(password)) {
            this.isArmed = false;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

}
