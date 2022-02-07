package com.shapeshifters.thecrash.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.shapeshifters.thecrash.service.Player;
import com.shapeshifters.thecrash.service.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TheCrashApp {
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private static final Scanner in = new Scanner(System.in);
    private static boolean gameOver = false;
    private String currentRoom = "Berthing";
    private Map<String, Room> rooms;
    private Map<String, String> verbs;
    private Map<String, String> directions;
    private Map<String, String> views;
    private Map<String, Player> players;
    private Map<String, String> items;
    private Map<String, String> shapeShifters;
    private Player player;
    private final inspectController inspect = new inspectController();
    private final ShapeShiftersController shapeShiftersController = new ShapeShiftersController();

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
            printBanner(currentRoom);
            String[] response = prompter.prompt("What would you like to do?\n").toLowerCase().split(" ");
            if (response.length == 0) {
                System.out.println("Invalid input: response must contain at least one word");
                promptEnterKey();
            } else {
                String verb = verbChecker(response);
                if (!verb.equals("null")) {
                    switch (verb) {
                        case "go":
                            setCurrentRoom(go(response));
                            break;
                        case "look":
                            look(response);
                            break;
                        case "use":
                            use(response);
                            break;
                        case "inspect":
                            inspect.inspect(response, getPlayer());
                            break;
                        case "get":
                            pickUpItem(response);
                            break;
                        case "remove":
                            removeItem(response);
                            break;
                        case "view":
                            view(response);
                            break;
                        case "q":
                        case "quit":
                            setGameOver(true);
                            break;
                        case "i":
                            viewInfo();
                            break;
                    }
                } else {
                    System.out.println(" Command not recognized.\n" +
                            "Try using words like go, look, use, inspect, get, remove, and view.\n" +
                            "For additional help enter I for information screen.");
                    promptEnterKey();
                }
            }
        }
    }

    private void use(String[] response) {
        for (String word:response) {
            if (("medkit".equals(word) || "kit".equals(word)) && player.getItems().contains("med kit")){
                if (player.getHealth()<100){
                        long health = player.getHealth();
                        health += 25;
                        player.setHealth(health);
                        System.out.println("25 health points have been added to your health");
                        player.getItems().remove("med kit");
                } else {
                        System.out.println("You are already at max health");
                }
            } else {
                System.out.println("Type 'use med kit' to use the med kit and add 25 health if it is in your inventory");
            }
        }
    }

    private String verbChecker(String[] response) {
        String result = "null";
        for (String word : response) {
            if (verbs.containsKey(word)) {
                result = verbs.get(word);
            }
        }
        return result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void setUp() {
        Map<String, Room> setUpRoomsMap = new HashMap<>();
        JSONParser jsonparser = new JSONParser();

        try {
            FileReader reader = new FileReader(String.valueOf(Path.of("resources", "rooms.json")));
            Object obj = jsonparser.parse(reader);
            JSONArray roomArray = (JSONArray) obj;

            for (Object o : roomArray) {
                JSONObject roomJsonObject = (JSONObject) o;
                JSONObject exitsObject = (JSONObject) roomJsonObject.get("exits");
                JSONObject viewsObject = (JSONObject) roomJsonObject.get("views");
                JSONArray itemsArray = (JSONArray) roomJsonObject.get("items");
                JSONArray inventoryArray = (JSONArray) roomJsonObject.get("inventory");
                Map<String, String> exits = new HashMap<>(exitsObject);
                Map<String, String> views = new HashMap<>(viewsObject);
                Collection<String> items = new ArrayList<>(itemsArray);
                Collection<String> inventory = new ArrayList<>(inventoryArray);
                setUpRoomsMap.put((String) roomJsonObject.get("name"), new Room((String) roomJsonObject.get("name"), (String) roomJsonObject.get("description"), exits, views, items, inventory));

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.setRooms(setUpRoomsMap);

        Map<String, Player> playerSetUpMap = new HashMap<>();
        try {
            FileReader reader2 = new FileReader(String.valueOf(Path.of("resources", "player.json")));
            Object obj2 = jsonparser.parse(reader2);
            JSONArray playersArray = (JSONArray) obj2;
            for (Object o: playersArray) {
                JSONObject playerJsonObject = (JSONObject) o;
                String name = (String) playerJsonObject.get("name");
                JSONArray items = (JSONArray) playerJsonObject.get("items");
                ArrayList<String> itemsList = new ArrayList<>(items);
                long health = (long) playerJsonObject.get("health");
                String currentRoom = (String) playerJsonObject.get("current room");
                playerSetUpMap.put(name, new Player(name,getRooms().get(currentRoom),health, itemsList));

            }


        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        setPlayers(playerSetUpMap);
        setPlayer(players.get("John"));
    }


    private String go(String[] response) {
        String dir = checkDirection(response);
        String currentRoom = player.getCurrentRoom().getName();
        String result = currentRoom;
        if (!dir.equals("null")) {
            boolean isDirectionValid = player.isDesiredDirectionValid(dir);
            if (isDirectionValid) {
                if ("Bridge".equals(currentRoom) && "aft".equals(dir)) {
                    String input = prompter.prompt("\nChoose 1 to go to Berthing\nChoose 2 to go to Mess Hall\n",
                            "[1-2]","Invalid response: Please select 1 or 2.");
                    switch (input) {
                        case "1":
                            result = "Berthing";
                            break;
                        case "2":
                            result = "Mess Hall";
                            break;
                    }
                } else if ("Engineering".equals(currentRoom) && "forward".equals(dir)) {
                    String input = prompter.prompt("\nChoose 1 to go to Berthing\nChoose 2 to go to Mess Hall\n",
                            "[1-2]","Invalid response: Please select 1 or 2.");
                    switch (input) {
                        case "1":
                            result ="Armory";
                            break;
                        case "2":
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
            getPlayer().setCurrentRoom(getRooms().get(result));

        } else {
            directionError();
        }
        return result;
    }

    private void directionError() {
        System.out.println("Direction not recognized.\n" +
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

    private String checkView(String[] view) {
        String result = "null";
        for (String word : view) {
            if (views.containsKey(word)) {
                result = views.get(word);
            }
        }
        return result;
    }

    private void view(String[] response){
        String viewItem = checkView(response);

        if(!viewItem.equals("null")){
            switch (viewItem) {
                case "map":
                    viewMap(player);
                    break;
                case "status":
                    viewStatus();
                    break;
                case "inventory":
                    viewInventory();
                    break;
                case "health":
                    viewHealth();
                    break;
            }

        }
    }

    private void look(String[] response) {
        String dir = checkDirection(response);
        if (!dir.equals("null")) {
            Console.clear();
            System.out.println(player.lookAt(dir));
        } else {
            directionError();
        }
        promptEnterKey();
    }

    private void startMainMenu() {
        Console.clear();
        Console.blankLines(2);
        printBanner("opening");
        pause(4);
        String choice;
        do {
            Console.clear();
            printBanner("menu");
            choice = prompter.prompt(">>","[1-3]","Invalid option: Please select between 1 - 3");

            switch (choice) {

                case "1":
                    //call berthing to start game
                    break;

                case "2":
                    Console.clear();
                    viewInfo();
                    break;

                case "3":
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println(choice + " is not a valid Menu Option! Please Select Another.");

            }
        }
        while (!"1".equals(choice));
    }

    private void viewInventory() {
        Collection<String> items = player.getItems();
        if (items.size() == 0) {
            System.out.println("You don't have any items in your inventory");
        } else {
            int counter = 1;
            System.out.println("You have " + items.size() + " items in your inventory list");
            for (String item : items) {
                System.out.println((counter) + ". " + item);
                counter++;
            }
        }
        promptEnterKey();
    }

    private void pickUpItem(String[] response){
        System.out.println(getItem(response));
        promptEnterKey();
    }

    private String getItem(String[] response){
        String item = itemChecker(response);
        String message = "";
        if(!item.equals("null")){
            if(player.getCurrentRoom().isItemInRoomInventory(item) || player.getCurrentRoom().isItemInRoomDroppedItems(item)){
                if(player.getItems().contains(item)){
                    message = "Item is already in the inventory";
                }else if (!player.isItemSizeUnderLimit()){
                    message = "You already have the max limit of items, you need to drop one";
                }else{
                    boolean didWin = true;
                    if (shapeShifters.containsKey(item)){
                        didWin = shapeShiftersController.encounterShapeShifter(getPlayer(), item);
                    }
                    if (didWin){
                        player.pickUpItem(item);
                        removeItemInRoomWhenPickedUp(item);
                        message = "You have successfully added the item to your inventory";
                    }

                }
            }else{
                message = "item is not in the current room, please go the room where the item is located";
            }
        }else{
            message = "Invalid item, is the item spelled correctly?";
        }
        return message;
    }

    private String itemChecker(String[] response) {
        String result = "null";
        for (String word: response) {
            if (items.containsKey(word)) {
                result = items.get(word);
            }
        }
        return result;
    }


    private void removeItemInRoomWhenPickedUp(String item) {
        if (player.getCurrentRoom().getDroppedItems().contains(item)) {
            player.getCurrentRoom().getDroppedItems().remove(item);
        } else {
            player.getCurrentRoom().getInventory().remove(item);
            player.getCurrentRoom().getItems().remove(item);
        }
    }
    private void removeItem(String[] response){
        String item = itemChecker(response);
        if(!item.equals("null")){
            if(player.isItemInInventory(item)){
                player.dropItem(item);
                player.getCurrentRoom().addToDroppedItemInventory(item);
            }
        }else{
            System.out.println("item invalid, please check your spelling");
        }

    }



    @SuppressWarnings("unchecked")
    private void loadWords() {
        JSONParser parser = new JSONParser();
        verbs = new HashMap<>();
        directions = new HashMap<>();
        views = new HashMap<>();
        items = new HashMap<>();
        shapeShifters = new HashMap<>();

        try {
            Object obj = parser.parse(new FileReader(String.valueOf((Path.of("resources", "verbs.json")))));
            Object obj1 = parser.parse(new FileReader(String.valueOf((Path.of("resources", "directions.json")))));
            Object obj2 = parser.parse(new FileReader(String.valueOf((Path.of("resources", "views.json")))));
            Object obj3 = parser.parse(new FileReader(String.valueOf((Path.of("resources", "inventory.json")))));
            Object obj4 = parser.parse(new FileReader(String.valueOf((Path.of("resources", "shapeshifters.json")))));

            JSONObject directionWords = (JSONObject) obj1;
            JSONObject actionWords = (JSONObject) obj;
            JSONObject viewsWords = (JSONObject) obj2;
            JSONObject roomItems = (JSONObject) obj3;
            JSONObject shapeShifterItems = (JSONObject) obj4;
            verbs.putAll(actionWords);
            directions.putAll(directionWords);
            views.putAll(viewsWords);
            items.putAll(roomItems);
            shapeShifters.putAll(shapeShifterItems);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void introduction() {
        printBanner("introduction");
        promptEnterKey();
    }

    private static void printBanner(String banner) {
        String fileName = banner.replaceAll("\\s", "").toLowerCase();
        Console.clear();
        Console.blankLines(2);
        try {
            Files.lines(Path.of("resources", fileName + ".txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private static void viewInfo() {

        printBanner("info");
        promptEnterKey();
    }

    static void viewMap(Player player) {
        if(player.isItemInInventory("map")){
            printBanner("map");
        }else{
            System.out.println("You don't have a map in your possession.");
        }
        promptEnterKey();
    }

    private void viewHealth() {
        System.out.println("Current Health: " + player.getHealth());
        promptEnterKey();
    }

    private void viewStatus(){
        writeStatus();
        printBanner("status");
        promptEnterKey();
    }

    private void writeStatus() {
       String currentRoom = "Location: " + getCurrentRoom();
       String health = "Health: " + player.getHealth();
       String items = "";
       int counter = 1;


       for(String item : player.getItems()){
            items += (counter + ". " + item + "\n");
            counter++;
        }

       String fileName = "resources/status.txt";
       String data = currentRoom + "\n" + health + "\n" + items;

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < lines.size(); i++){
            if(i > 0 && i < lines.size() - 1){
                if(i == 1){
                    lines.set(i, "");
                } else {
                    lines.remove(lines.get(i));
                    i -=1;
                }
            }
        }

        lines.set(1, data);
        try {
            Files.write(Path.of(fileName), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //GETTERS AND SETTERS
    public boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        TheCrashApp.gameOver = gameOver;
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

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }
}