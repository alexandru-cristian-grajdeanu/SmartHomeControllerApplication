package com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions;

public class NoDeviceFoundException extends Throwable {
    public NoDeviceFoundException(String deviceNotFound) {
        super(deviceNotFound);
    }
}
