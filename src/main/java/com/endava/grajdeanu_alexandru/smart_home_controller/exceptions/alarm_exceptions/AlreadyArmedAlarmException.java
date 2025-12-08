package com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions;

public class AlreadyArmedAlarmException extends Throwable {
    public AlreadyArmedAlarmException(String alarmSystemIsAlreadyArmed) {
        super(alarmSystemIsAlreadyArmed);
    }
}
