package com.shapeshifters.thecrash.controller;

import com.apps.util.Console;
import com.shapeshifters.thecrash.service.Player;
import com.shapeshifters.thecrash.service.Room;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TheCrashApp {
    private boolean gameOver = false;
    private String currentRoom = "Berthing";
    private Map<String, Room> rooms;
    private static final Scanner in = new Scanner(System.in);
    private Player player;
    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS
    public void execute(){
        Scanner in = new Scanner(System.in);
        setUp();
        startMainMenu();
        while (!isGameOver()){
            System.out.println("You are now in " + currentRoom);
            System.out.println("What would you like to do?");
            String[] response = in.nextLine().toLowerCase().split(" ");
            if ("look".equals(response[0])){
                look(response[1]);
            } else if ("go".equals(response[0])){
                currentRoom = go(currentRoom, response[1]);
                getPlayer().setCurrentLocation(getRooms().get(currentRoom));
            }
        }
    }

    public void setUp(){
        Map<String, Room> setUpRoomsMap = new HashMap<>();
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);

        Map<String, String> messHallExits = new HashMap<>();
        messHallExits.put("aft", "Med Bay");
        messHallExits.put("stbd", "Berthing");
        messHallExits.put("starboard", "Berthing");
        messHallExits.put("forward", "Bridge");
        Room messHall = new Room("Mess Hall","This is were everybody eats", messHallExits);

        Map<String, String> armoryExits = new HashMap<>();
        armoryExits.put("aft", "Engineering");
        armoryExits.put("port", "Med Bay");
        armoryExits.put("forward", "Berthing");
        Room armory = new Room("Armory","This is where all the weapons are held.", armoryExits);

        Map<String, String> medBayExits = new HashMap<>();
        medBayExits.put("aft", "Engineering");
        medBayExits.put("stbd", "Armory");
        medBayExits.put("starboard", "Armory");
        medBayExits.put("forward", "Mess Hall");
        Room medBay = new Room("Med Bay","If you need fixing, then this is the place", medBayExits);

        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");
        Room engineering = new Room("Engineering","The Engineering place", engineeringExits);

        Map<String, String> bridgeExits = new HashMap<>();
        bridgeExits.put("Aft1", "Berthing");
        bridgeExits.put("Aft2", "Mess Hall");
        Room bridge = new Room("Bridge","Check out the scene", bridgeExits);

        setUpRoomsMap.put("Berthing", berthing);
        setUpRoomsMap.put("Armory", armory);
        setUpRoomsMap.put("Mess Hall", messHall);
        setUpRoomsMap.put("Med Bay", medBay);
        setUpRoomsMap.put("Engineering", engineering);
        setUpRoomsMap.put("Bridge", bridge);
        this.setRooms(setUpRoomsMap);
        setPlayer(new Player("Armando", getRooms().get("Berthing")));
    }

    private String go(String currentRoom, String dir){
        return player.goToAdjacentRoom(dir);
    }

    private void look(String dir){
        switch (dir){
            case "forward":
                lookForward(currentRoom.toLowerCase());
                break;
            case "aft":
                lookAft(currentRoom.toLowerCase());
                break;
            case "port":
                lookPort(currentRoom.toLowerCase());
                break;
            case "stbd":
            case "starboard":
                lookStbd(currentRoom.toLowerCase());
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

    public void startMainMenu() {
        int choice;
        do {
            System.out.println("Tʜᴇ Cʀᴀsʜ!\n" +
                    "Wʜᴏ's ᴅʀɪᴠɪɴɢ ᴛʜɪs ᴛʜɪɴɢ ᴀɴʏᴡᴀʏ?");
            System.out.println("==========================================");
            System.out.println("The Crash, Main Menu\n");
            System.out.print("1.) Start Game \n");
            System.out.print("2.) Instructions\n");
            System.out.print("3.) Exit\n");
            choice = in.nextInt();

            switch (choice) {

                case 1:
                    //call berthing to start game
                    break;

                case 2:
                    System.out.println("The player can move forward, aft, port, and starboard(stbd) to explore\n" +
                            "different rooms in the ship. You have to find items by exploring the different rooms\n" +
                            "and use those items to repair the ship! ");
                    break;

                case 3:
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println(choice + " is not a valid Menu Option! Please Select Another.");

            }
        }
        while (choice != 1 /*Exit loop when choice is 4*/);
    }

    private static void printBanner(String banner) {
        Console.clear();
        Console.blankLines(2);
        try {
            Files.lines(Path.of("resources", banner + ".txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.blankLines(2);
    }

    private static void pause(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}