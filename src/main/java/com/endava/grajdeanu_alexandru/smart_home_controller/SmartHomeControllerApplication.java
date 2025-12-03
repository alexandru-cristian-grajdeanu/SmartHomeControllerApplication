package com.endava.grajdeanu_alexandru.smart_home_controller;

import com.endava.grajdeanu_alexandru.smart_home_controller.config.ProjectConfig;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.AlarmSystem;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.SmartAssistant;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.LightBulb;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.devices.roomDevices.Thermostat;
import com.endava.grajdeanu_alexandru.smart_home_controller.entities.rooms.Room;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.DeviceService;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.RoomService;
import com.endava.grajdeanu_alexandru.smart_home_controller.services.SmartHomeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class SmartHomeControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeControllerApplication.class, args);
//        var config = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        var deviceService = config.getBean(DeviceService.class);
//        var roomService = config.getBean(RoomService.class);
//        var smartHomeService = config.getBean(SmartHomeService.class);
//        var alarmSystem = config.getBean("alarmSystem", AlarmSystem.class);
//        System.out.println(alarmSystem);
//        var smartAssistant = config.getBean("smartAssistant", SmartAssistant.class);
//        System.out.println(smartAssistant);
//        deviceService.addDevice(alarmSystem);
//        deviceService.addDevice(smartAssistant);
//        while (true) {
//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter command: ");
//            String command = input.nextLine();
//            switch (command) {
//                case "add_device":
//                    System.out.print("For what room? Write room id: ");
//                    String roomId = input.nextLine();
//                    if (roomService.getAllRooms().stream().noneMatch(r -> r.getIdRoom().equals(roomId))) {
//                        System.out.println("Room does not exist. Please add the room first.");
//                        break;
//                    }
//                    System.out.print("What type of device?");
//                    String deviceType = input.nextLine();
//                    if(deviceType.equals("thermostat")){
//                        var thermostat = deviceService.addDevice(new Thermostat(roomId));
//                        System.out.println("Thermostat created: " + thermostat);
//                    } else if (deviceType.equals("light_bulb")) {
//                        System.out.println("Tell us the intensity level (1-6800): ");
//                        String deviceId = input.nextLine();
//                        var lightBulb = deviceService.addDevice(new LightBulb(Integer.parseInt(deviceId), roomId));
//                        System.out.println("Light Bulb created: " + lightBulb);
//                    } else {
//                        System.out.println("Unknown device type.");
//                    }
//                    break;
//                case "list_devices":
//                    var devices = deviceService.getAllDevices();
//                    System.out.println("Devices: ");
//                    devices.forEach(System.out::println);
//                    break;
//                case "arm_alarm":
//                    System.out.println("Enter password: ");
//                    String armPassword = input.nextLine();
//                    try {
//                        smartHomeService.armAlarmSystem(armPassword);
//                        System.out.println("Alarm armed.");
//                    } catch (RuntimeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//
//                case "disarm_alarm":
//                    System.out.println("Enter password: ");
//                    String disarmPassword = input.nextLine();
//                    try {
//                        smartHomeService.disarmAlarmSystem(disarmPassword);
//                        System.out.println("Alarm disarmed.");
//                    } catch (RuntimeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "turn_off_room_devices":
//                    System.out.println("Enter room id: ");
//                    String roomIdToTurnOff = input.nextLine();
//                    try {
//                        smartHomeService.turnOffDevicesInRoom(roomIdToTurnOff);
//                        System.out.println("All devices in room " + roomIdToTurnOff + " have been turned off.");
//                    } catch (RuntimeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                case "help":
//                    System.out.println("Available commands: add_room, list_rooms, exit, add_device, list_devices");
//                    break;
//                case "exit":
//                    System.out.println("Exiting...");
//                    System.exit(0);
//                    break;
//                case "add_room":
//                    System.out.println("Enter room id: ");
//                    String newRoomId = input.nextLine();
//                    Room room = new Room(newRoomId);
//                    roomService.addRoom(room);
//                    System.out.println("Room added: " + room);
//                    break;
//                case "list_rooms":
//                    var rooms = roomService.getAllRooms();
//                    System.out.println("Rooms: ");
//                    rooms.forEach(System.out::println);
//                    break;
//                default:
//                    System.out.println("Unknown command.");
//            }
//        }
    }
}