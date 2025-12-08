package com.endava.grajdeanu_alexandru.smart_home_controller.controllers;

import com.endava.grajdeanu_alexandru.smart_home_controller.dtos.ErrorDetailsDTO;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyArmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.alarm_exceptions.AlreadyDisarmedAlarmException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DeviceAlreadyExistsException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.DisconnectedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.NoDeviceFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.devices_exceptions.UnsupportedDeviceException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.general_exceptions.InvalidValuesException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.NoRoomFoundException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.room_exceptions.RoomAlreadyExistsException;
import com.endava.grajdeanu_alexandru.smart_home_controller.exceptions.security_exceptions.IncorrectPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AlreadyArmedAlarmException.class)
    public ResponseEntity<ErrorDetailsDTO> handleAlreadyArmedAlarmException(AlreadyArmedAlarmException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(AlreadyDisarmedAlarmException.class)
    public ResponseEntity<ErrorDetailsDTO> handleAlreadyDisarmedAlarmException(AlreadyDisarmedAlarmException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(DeviceAlreadyExistsException.class)
    public ResponseEntity<ErrorDetailsDTO> handleDeviceAlreadyExistsException(DeviceAlreadyExistsException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(DisconnectedDeviceException.class)
    public ResponseEntity<ErrorDetailsDTO> handleDisconnectedDeviceException(DisconnectedDeviceException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(NoDeviceFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> handleNoDeviceFoundException(NoDeviceFoundException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(UnsupportedDeviceException.class)
    public ResponseEntity<ErrorDetailsDTO> handleUnsupportedDeviceException(UnsupportedDeviceException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(InvalidValuesException.class)
    public ResponseEntity<ErrorDetailsDTO> handleInvalidValuesException(InvalidValuesException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(NoRoomFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> handleNoRoomFoundException(NoRoomFoundException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<ErrorDetailsDTO> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorDetailsDTO> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }

}
