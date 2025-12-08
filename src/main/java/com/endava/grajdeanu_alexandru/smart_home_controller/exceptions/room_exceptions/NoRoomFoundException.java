package com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions;

public class NoRoomFoundException extends Throwable {
    public NoRoomFoundException(String roomNotFoundForLightBulb) {
        super(roomNotFoundForLightBulb);
    }
}
