package com.shapeshifters.thecrash.controller;

import com.shapeshifters.thecrash.service.Room;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class TheCrashApp {
    private boolean gameOver = false;
    private String currentRoom = "Berthing";
    private Map<String, Room> rooms;
    private static Scanner in = new Scanner(System.in);
    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS
    public void execute(){
        Scanner in = new Scanner(System.in);
        setUp();
        startMainMenu();
        introduction();
        while (!isGameOver()){
            System.out.println("You are now in " + currentRoom);
            System.out.println("What would you like to do?");
            String[] response = in.nextLine().toLowerCase().split(" ");
            if ("look".equals(response[0])){
                look(response[1]);
            } else if ("go".equals(response[0])){
                currentRoom = go(currentRoom, response[1]);
            }
        }
    }

    public void setUp(){
        Map<String, Room> setUpRoomsMap = new HashMap<>();
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("This is were everybody sleeps", berthingExits);

        Map<String, String> messHallExits = new HashMap<>();
        messHallExits.put("aft", "Med Bay");
        messHallExits.put("stbd", "Berthing");
        messHallExits.put("starboard", "Berthing");
        messHallExits.put("forward", "Bridge");
        Room messHall = new Room("This is were everybody eats", messHallExits);

        Map<String, String> armoryExits = new HashMap<>();
        armoryExits.put("aft", "Engineering");
        armoryExits.put("port", "Med Bay");
        armoryExits.put("forward", "Berthing");
        Room armory = new Room("This is where all the weapons are held.", armoryExits);

        Map<String, String> medBayExits = new HashMap<>();
        medBayExits.put("aft", "Engineering");
        medBayExits.put("stbd", "Armory");
        medBayExits.put("starboard", "Armory");
        medBayExits.put("forward", "Mess Hall");
        Room medBay = new Room("If you need fixing, then this is the place", medBayExits);

        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");
        Room engineering = new Room("The Engineering place", engineeringExits);

        Map<String, String> bridgeExits = new HashMap<>();
        bridgeExits.put("Aft1", "Berthing");
        bridgeExits.put("Aft2", "Mess Hall");
        Room bridge = new Room("Check out the scene", bridgeExits);

        setUpRoomsMap.put("Berthing", berthing);
        setUpRoomsMap.put("Armory", armory);
        setUpRoomsMap.put("Mess Hall", messHall);
        setUpRoomsMap.put("Med Bay", medBay);
        setUpRoomsMap.put("Engineering", engineering);
        setUpRoomsMap.put("Bridge", bridge);
        this.setRooms(setUpRoomsMap);

    }

    private String go(String currentRoom, String dir){

        String result = currentRoom;
        if ("Bridge".equals(currentRoom) && "aft".equals(dir)){
            System.out.println("\nChoose 1 to go to Berthing\nChoose 2 to go to Mess Hall\n");
            int input = in.nextInt();
            switch (input){
                case 1:
                    result = "Berthing";
                    break;
                case 2:
                    result = "Mess Hall";
                    break;
            }
        }else if ("Engineering".equals(currentRoom) && "forward".equals(dir)){
            System.out.println("\nChoose 1 to go to Armory\nChoose 2 to go to Med Bay\n");
            int input = in.nextInt();
            switch (input){
                case 1:
                    result = "Armory";
                    break;
                case 2:
                    result = "Med Bay";
                    break;
            }
        } else if (rooms.get(currentRoom).getExits().containsKey(dir)){
            result = rooms.get(currentRoom).getExits().get(dir);
        } else {
            System.out.println("You can't go in that direction");
        }
        return result;
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
    public void introduction(){
        System.out.println("                 ____\n" +
                "                /___.`--.____ .--. ____.--(\n" +
                "                       .'_.- (    ) -._'.\n" +
                "                     .'.'    |'..'|    '.'.\n" +
                "              .-.  .' /'--.__|____|__.--'\\ '.  .-.\n" +
                "             (O).)-| |  \\    |    |    /  | |-(.(O)\n" +
                "              `-'  '-'-._'-./      \\.-'_.-'-'  `-'\n" +
                "                 _ | |   '-.________.-'   | | _\n" +
                "              .' _ | |     |   __   |     | | _ '.\n" +
                "             / .' ''.|     | /    \\ |     |.'' '. \\\n" +
                "             | |( )| '.    ||      ||    .' |( )| |\n" +
                "             \\ '._.'   '.  | \\    / |  .'   '._.' /\n" +
                "              '.__ ______'.|__'--'__|.'______ __.'\n" +
                "             .'_.-|         |------|         |-._'.\n" +
                "            //\\\\  |         |--::--|         |  //\\\\\n" +
                "           //  \\\\ |         |--::--|         | //  \\\\\n" +
                "          //    \\\\|        /|--::--|\\        |//    \\\\\n" +
                "         / '._.-'/|_______/ |--::--| \\_______|\\`-._.' \\\n" +
                "        / __..--'        /__|--::--|__\\        `--..__ \\\n" +
                "       / /               '-.|--::--|.-'               \\ \\\n" +
                "      / /                   |--::--|                   \\ \\\n" +
                "     / /                    |--::--|                    \\ \\\n" +
                " _.-'  `-._                 _..||.._                  _.-` '-._\n" +
                "'--..__..--'               '-.____.-'                '--..__..-'\n");

        System.out.println("You wake up with a headache....It seems you have crash landed on a strange planet.\n" +
                "You put on your gear and head outside of the ship and talk to one of your crew mates....\n" +
                "they tell you this is a planet of shape shifters, so be careful. You are informed that there are broken parts in the ship that needs repaired\n" +
                "Your crew mates encourages you to head to the berthing and see what parts need to be repaired");
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