package com.shapeshifters.thecrash.controller;

import com.apps.util.Console;
import com.shapeshifters.thecrash.service.Player;
import com.shapeshifters.thecrash.service.Room;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TheCrashApp {
    private static final Scanner in = new Scanner(System.in);
    private boolean gameOver = false;
    private String currentRoom = "Berthing";
    private Map<String, Room> rooms;
    private Map<String, String> verbs;
    private Map<String, String> directions;
    private Player player;

    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS
    public void execute() {
        loadWords();
        setUp();
        startMainMenu();
        introduction();
        while (!isGameOver()) {
            Console.clear();
            System.out.println(player.getCurrentRoom().getExits());
            System.out.println("You are now in " + currentRoom);
            System.out.println("What would you like to do?");
            String[] response = in.nextLine().toLowerCase().split(" ");
            if (response.length == 0) {
                System.out.println("Invalid input: response must contain at least one word");
                promptEnterKey();
            } else {
                String verb = verbChecker(response);
                    if (!verb.equals("null")) {
                        switch (verb) {
                            case "go":
                                currentRoom = go(response);
                                break;
                            case "look":
                                look(response);
                                break;
                            case "use":
                                break;
                            case "inspect":
                                break;
                            case "get":
                                break;
                            case "remove":
                                break;
                            case "view":
                                break;
                            case "q":
                            case "quit":
                                break;
                        }
                    } else {
                        System.out.println("Command not recognized.\n" +
                                "Try using words like go, look, use, inspect, get, remove, and view.\n" +
                                "For additional help enter I for information screen.");
                        promptEnterKey();
                    }
            }
        }
    }

    private String verbChecker(String[] response) {
        String result = "null";
        for (String word: response) {
            if(verbs.containsKey(word)){
                result = verbs.get(word);
            }
        }
        return result;
    }

    public void setUp() {
        Map<String, Room> setUpRoomsMap = new HashMap<>();
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing", "This is were everybody sleeps", berthingExits);

        Map<String, String> messHallExits = new HashMap<>();
        messHallExits.put("aft", "Med Bay");
        messHallExits.put("stbd", "Berthing");
        messHallExits.put("starboard", "Berthing");
        messHallExits.put("forward", "Bridge");
        Room messHall = new Room("Mess Hall", "This is were everybody eats", messHallExits);

        Map<String, String> armoryExits = new HashMap<>();
        armoryExits.put("aft", "Engineering");
        armoryExits.put("port", "Med Bay");
        armoryExits.put("forward", "Berthing");
        Room armory = new Room("Armory", "This is where all the weapons are held.", armoryExits);

        Map<String, String> medBayExits = new HashMap<>();
        medBayExits.put("aft", "Engineering");
        medBayExits.put("stbd", "Armory");
        medBayExits.put("starboard", "Armory");
        medBayExits.put("forward", "Mess Hall");
        Room medBay = new Room("Med Bay", "If you need fixing, then this is the place", medBayExits);

        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");
        Room engineering = new Room("Engineering", "The Engineering place", engineeringExits);

        Map<String, String> bridgeExits = new HashMap<>();
        bridgeExits.put("Aft1", "Berthing");
        bridgeExits.put("Aft2", "Mess Hall");
        Room bridge = new Room("Bridge", "Check out the scene", bridgeExits);

        setUpRoomsMap.put("Berthing", berthing);
        setUpRoomsMap.put("Armory", armory);
        setUpRoomsMap.put("Mess Hall", messHall);
        setUpRoomsMap.put("Med Bay", medBay);
        setUpRoomsMap.put("Engineering", engineering);
        setUpRoomsMap.put("Bridge", bridge);
        this.setRooms(setUpRoomsMap);
        setPlayer(new Player("Armando", getRooms().get("Berthing")));
    }


    public String go(String[] response) {
        String dir = checkDirection(response);
        String currentRoom = player.getCurrentRoom().getName();
        String result = currentRoom;
        if (!dir.equals("null")) {
            boolean isDirectionValid = player.isDesiredDirectionValid(dir);
            if (isDirectionValid) {
                if ("Bridge".equals(currentRoom) && "aft".equals(dir)) {
                    System.out.println("\nChoose 1 to go to Berthing\nChoose 2 to go to Mess Hall\n");
                    int input = in.nextInt();
                    switch (input) {
                        case 1:
                            result = "Berthing";
                            break;
                        case 2:
                            result = "Mess Hall";
                            break;
                    }
                } else if ("Engineering".equals(currentRoom) && "forward".equals(dir)) {
                    System.out.println("\nChoose 1 to go to Armory\nChoose 2 to go to Med Bay\n");
                    int input = in.nextInt();
                    switch (input) {
                        case 1:
                            result = "Armory";
                            break;
                        case 2:
                            result = "Med Bay";
                            break;
                    }
                } else {
                    result = player.getCurrentRoom().getExits().get(dir);
                }
            } else {
                System.out.println("You can't go in that direction");
                promptEnterKey();
            }
            getPlayer().setCurrentRoom(getRooms().get(currentRoom));

        } else {
            directionError();
        }
        return result;
    }

    private void directionError() {
        System.out.println("Command not recognized.\n" +
                "Try using words like port, starboard, forward, aft, left, right, ahead, and behind.\n" +
                "For additional help enter I for the information screen.");
        promptEnterKey();
    }

    private String checkDirection(String[] dir) {
        String result = "null";
        for (String word : dir) {
            if (directions.containsKey(word)) {
                result = directions.get(word);
            }
        }
        return result;
    }

    private void look(String[] response) {
        String dir = checkDirection(response);
        if (!dir.equals("null")) {
            switch (dir) {
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
        } else {
            directionError();
        }

    }

    private void lookForward(String room) {
        Console.clear();
        switch (room) {
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
        promptEnterKey();
    }

    private void lookAft(String room) {
        Console.clear();
        switch (room) {
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
        promptEnterKey();
    }

    private void lookPort(String room) {
        Console.clear();
        switch (room) {
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
        promptEnterKey();
    }

    private void lookStbd(String room) {
        Console.clear();
        switch (room) {
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
        promptEnterKey();
    }

    public void startMainMenu() {
        Console.clear();
        Console.blankLines(2);
        printBanner("opening");
        pause(4);
        int choice;
        do {
            Console.clear();
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
                    Console.clear();
                    System.out.println("The player can move forward, aft, port, and starboard(stbd) to explore\n" +
                            "different rooms in the ship. You have to find items by exploring the different rooms\n" +
                            "and use those items to repair the ship!\n");
                    promptEnterKey();
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

    @SuppressWarnings("unchecked")
    private void loadWords() {
        JSONParser parser = new JSONParser();
        verbs = new HashMap<>();
        directions = new HashMap<>();
        try {
            Object obj = parser.parse(new FileReader(String.valueOf((Path.of("resources", "verbs.json")))));
            Object obj1 = parser.parse(new FileReader(String.valueOf((Path.of("resources", "directions.json")))));
            JSONObject directionWords = (JSONObject) obj1;
            JSONObject actionWords = (JSONObject) obj;
            verbs.putAll(actionWords);
            directions.putAll(directionWords);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void introduction() {
        printBanner("introduction");
        promptEnterKey();
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

    private static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void pause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void viewMap() {
        printBanner("map");
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