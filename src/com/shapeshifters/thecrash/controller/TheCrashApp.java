package com.shapeshifters.thecrash.controller;

import com.shapeshifters.thecrash.service.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheCrashApp {
    private boolean gameOver = false;
    private String currentRoom = "berthing";
    private Map<String, Room> rooms;

    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS


    public void setUp(){
        Map<String, Room> setUpRoomsMap = new HashMap<>();
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("Aft", "Armory");
        berthingExits.put("Port", "Mess Hall");
        berthingExits.put("Forward", "Bridge");
        Room berthing = new Room("This is were everybody sleeps", berthingExits);

        Map<String, String> messHallExits = new HashMap<>();
        messHallExits.put("Aft", "Armory");
        messHallExits.put("Port", "Mess Hall");
        messHallExits.put("Forward", "Bridge");
        Room messHall = new Room("This is were everybody eats", messHallExits);

        Map<String, String> armoryExits = new HashMap<>();
        armoryExits.put("Aft", "Engineering");
        armoryExits.put("Port", "Med Bay");
        armoryExits.put("Forward", "Berthing");
        Room armory = new Room("This is where all the weapons are held.", armoryExits);

        Map<String, String> medBayExits = new HashMap<>();
        medBayExits.put("Aft", "Engineering");
        medBayExits.put("Starboard", "Armory");
        medBayExits.put("Forward", "Mess Hall");
        Room medBay = new Room("If you need fixing, then this is the place", medBayExits);

        Map<String, String> engineeringExits = new HashMap<>();
        medBayExits.put("Forward1", "Armory");
        medBayExits.put("Forward2", "Med Bay");
        Room engineering = new Room("The Engineering place", engineeringExits);

        Map<String, String> bridgeExits = new HashMap<>();
        medBayExits.put("Aft1", "Berthing");
        medBayExits.put("Aft2", "Mess Hall");
        Room bridge = new Room("Check out the scene", bridgeExits);

        setUpRoomsMap.put("Berthing", berthing);
        setUpRoomsMap.put("Armory", armory);
        setUpRoomsMap.put("Mess Hall", messHall);
        setUpRoomsMap.put("Med Bay", medBay);
        setUpRoomsMap.put("Engineering", engineering);
        setUpRoomsMap.put("Bridge", bridge);
        this.setRooms(setUpRoomsMap);

    }
    public void execute(){
        Scanner in = new Scanner(System.in);
        setUp();

        while (!isGameOver()){
            System.out.println("What would you like to do?");
            String[] response = in.nextLine().toLowerCase().split(" ");
            if ("look".equals(response[0])){
                look(response[1]);
            }
        }
    }

    private void look(String dir){
        switch (dir){
            case "forward":
                lookForward(currentRoom);
                break;
            case "aft":
                lookAft(currentRoom);
                break;
            case "port":
                lookPort(currentRoom);
                break;
            case "stbd":
            case "starboard":
                lookStbd(currentRoom);
                break;
            default:
                System.out.println("I don't understand which way you want to look");
        }
    }

    private void lookForward(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the forward bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the forward bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the forward bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the forward bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the forward bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the forward bulkhead in engineering");
                break;
        }
    }

    private void lookAft(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the aft bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the aft bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the aft bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the aft bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the aft bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the aft bulkhead in engineering");
                break;
        }
    }

    private void lookPort(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the port bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the port bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the port bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the port bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the port bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the port bulkhead in engineering");
                break;
        }
    }

    private void lookStbd(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the starboard bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the starboard bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the starboard bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the starboard bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the starboard bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the starboard bulkhead in engineering");
                break;
        }
    }


    //GETTERS AND SETTERS
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Room> rooms) {
        this.rooms = rooms;
    }
}