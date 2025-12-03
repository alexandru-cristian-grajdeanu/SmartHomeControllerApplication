package com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Getter
public class AlarmSystem extends Device {
    private boolean isArmed;
    private String password;

    public AlarmSystem(String password) {
        super("ALARM_DEVICE");
        this.isArmed = false;
        this.password  = password;
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
}
