package com.shapeshifters.thecrash.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.shapeshifters.thecrash.service.Player;
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

class inspectController {
    private final Map<String,String> items;
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private boolean isFryerEmpty = false;
    private boolean isCabinetOpen = false;
    private boolean isEngineFixed = false;
    private boolean isPanelFixed = false;
    private boolean isPodOpen = false;

    public inspectController() {
        this.items = loadItems();
    }

    public void inspect(String[] input, Player player){
        String item = itemChecker(input);
        if(!"null".equals(item)){
            if (player.getCurrentRoom().getItems().contains(item) ||
                    player.getCurrentRoom().getDroppedItems().contains(item) ||
                    player.getItems().contains(item)){
                switch (item){
                    case "pods":
                        inspectPods();
                        break;
                    case "locker":
                        inspectLocker(player);
                        break;
                    case "board":
                        inspectBoard(player);
                        break;
                    case "map":
                        inspectMap(player);
                        break;
                    case "panel":
                        inspectPanel(player);
                        break;
                    case "seat":
                        inspectSeat(player);
                        break;
                    case "rack":
                        inspectRack(player);
                        break;
                    case "stove":
                        inspectStove(player);
                        break;
                    case "fryer":
                        inspectFryer(player);
                        break;
                    case "manual":
                        inspectManual();
                        break;
                    case "desk":
                        inspectDesk(player);
                        break;
                    case "cabinet":
                        inspectCabinet(player);
                        break;
                    case "medkit":
                        inspectMedkit();
                        break;
                    case "container":
                        inspectContainer(player);
                        break;
                    case "shelf":
                        inspectShelf(player);
                        break;
                    case "toolbox":
                        inspectToolbox(player);
                        break;
                    case "engine":
                        inspectEngine(player);
                        break;
                    case "bolt cutter":
                    case "bucket":
                    case "screwdriver":
                    case "wire":
                    case "tray":
                    case "pot":
                    case "paper clip":
                    case "bed":
                    case "bubble gum":
                        inspectGeneric(item);
                        break;
                }
            }

        }else {
            System.out.println("Invalid Object: Try typing the names of items in the room description.");
            prompter.prompt("Press ENTER to continue...");
        }
    }

    private void inspectStove(Player player) {
        if (player.getCurrentRoom().getItems().contains("pot")){
            System.out.println("You see a stove with a large empty pot on one of the burners.\n" +
                    "It appears as though the crash must have damaged it because it won't turn on.\n");
        } else {
            System.out.println("You see a stove with 4 large burners.\n" +
                    "It appears as though the crash must have damaged it because it won't turn on.\n");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectFryer(Player player) {
        if (!isFryerEmpty()){
            System.out.println("It looks like the cook forgot to drain the old oil from the fryer.\n" +
                    "There is a valve on the side of the fryer that is used for draining the oil.\n" +
                    "When you try to operate the valve, you get an error that says:\n" +
                    "Container must be in place before fryer will drain.\n");
            if(player.getItems().contains("pot") || player.getItems().contains("bucket")){
                System.out.println("\nIt looks like you might have something in your inventory that would work.");
                String container;
                if(player.getItems().contains("pot")){
                    container = "pot";
                }else {
                    container = "bucket";
                }
                String response = prompter.prompt("Would you like to use the " + container + "? (Y/N)","y|Y|n|N",
                        "Please select Y or N. ").toLowerCase();
                if ("y".equals(response)){
                    player.getItems().remove(container);
                    player.getItems().add("hose");
                    setFryerEmpty(true);
                    System.out.println("You place the " + container + " next to the fryer and try the valve again.\n" +
                            "This time the oil drains to reveal a hose that it used to drain the oil.\n" +
                            "You remove the hose and add it to your inventory.\n");
                }
            }else {
                System.out.println("It doesn't look like you have anything in your inventory that would work.\n");
            }
        } else {
            System.out.println("You see an empty fryer.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectManual() {
        Console.clear();
        Console.blankLines(2);
        try {
            Files.lines(Path.of("resources", "manual" + ".txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectDesk(Player player) {
        System.out.println("On the top of the desk you see a paperclip.\n" +
                "The only thing you see in the drawers is a Cryo-bed manual");
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectCabinet(Player player) {
        if (!isCabinetOpen()){
            System.out.println("It appears that the medicine cabinet is locked with a 3 digit combination padlock.\n");
            String response = prompter.prompt("Would you like to enter the combination? (y/n)","y|Y|n|N",
                    "Please select Y or N. ").toLowerCase();
            if ("y".equals(response)){
                while (true){
                    String answer = prompter.prompt("Please enter the combination or L to leave.").toLowerCase();
                    if ("954".equals(answer)){
                        setCabinetOpen(true);
                        System.out.println("The lock opens and you see a med kit inside the cabinet");
                        System.out.println("med kit has been added to your inventory.\n" +
                                "Type 'use med kit' at anytime to regain 25 health points");
                        player.getItems().add("med kit");
                        player.getCurrentRoom().getItems().remove("med kit");
                        player.getCurrentRoom().getItems().remove("med kit");
                        break;
                    } else if ("l".equals(answer)){
                        break;
                    }else {
                        System.out.println("Incorrect combination. Please try again.");
                    }
                }
            } else {
                if (player.getItems().contains("bolt cutter")){
                    String answer = prompter.prompt("Would you like to cut the lock with the bolt cutters? (y/n)",
                            "y|Y|n|N","Please select Y or N.").toLowerCase();
                    if ("y".equals(answer)){
                        System.out.println("The lock breaks and you see a med kit inside the cabinet");

                    }
                }
            }
        }else{
            if (player.getCurrentRoom().getItems().contains("medkit")){
                System.out.println("You see a med kit inside the cabinet");
            } else {
                System.out.println("There is nothing else inside the cabinet");
            }
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectMedkit() {
        System.out.println("This is a standard med kit.\n When used, it will restore 25 hit points to your heath");
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectContainer(Player player) {
        if (player.getCurrentRoom().getItems().contains("bubble gum")){
            System.out.println("Inside the crew storage container you see a pack of bubblegum.");
        } else {
            System.out.println("The container is now empty");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectShelf(Player player) {
        if (player.getCurrentRoom().getItems().contains("screwdriver")){
            System.out.println("You see a screwdriver on the shelf");
        } else {
            System.out.println("The shelves are empty");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectToolbox(Player player) {
        if (player.getCurrentRoom().getItems().contains("soldering iron")){
            System.out.println("Inside the toolbox you see a soldering iron");
        } else {
            System.out.println("The toolbox is empty.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectEngine(Player player) {
        if (!isEngineFixed()){
            System.out.println("It looks like the hose that connects the cooling tower to the reactor burst");
            if (player.isItemInInventory("screwdriver") && !player.isItemInInventory("hose")){
                System.out.println("You have a screwdriver to remove the clamps holding the old hose in place,\n" +
                        "but you still need a new hose to replace the broken one.");
            } else if (player.isItemInInventory("hose") && !player.isItemInInventory("screwdriver")){
                System.out.println("You have a hose that can be used to replace the broken one,\n" +
                        "but you need a screwdriver to remove the clamps holding the hose in place.");
            } else if (!player.isItemInInventory("hose") && !player.isItemInInventory("screwdriver")){
                System.out.println("You will need a new hose to replace the broken one and a screwdriver\n" +
                        "to remove the clamps holding the hose in place");
            } else {
                System.out.println("\nYou have everything you need to fix the engine\n");
                String answer = prompter.prompt("\nWould you like to replace the broken hose? (y/n)",
                        "y|Y|n|N","Please select Y or N.").toLowerCase();
                if ("y".equals(answer)){
                    player.getItems().remove("hose");
                    setEngineFixed(true);
                    System.out.println("You use the screwdriver to remove the old broken hose and replace it\n" +
                            "with the new one. After checking the reactor control panel, it looks\n" +
                            "like the engine is fixed. Great job!");
                } else {
                    System.out.println("Ok, but this engine needs to get fixed before we can leave.");
                }
            }
        } else {
            System.out.println("The engine is fixed, LEAVE IT ALONE");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectRack(Player player) {
        if (player.getCurrentRoom().getItems().contains("tray")){
            System.out.println("On the automated tray rack is a single tray, it looks like the rest are trapped inside");
        } else {
            System.out.println("The automated tray rack is jammed, there are no more trays.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectSeat(Player player) {
        if (player.getCurrentRoom().getItems().contains("wire")){
            System.out.println("Under one of the seats you see a piece of electrical wire. You're not sure where it\n" +
                    "came from, but it might be useful.");
        } else {
            System.out.println("You see some dust bunnies.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectMap(Player player) {
        TheCrashApp.viewMap(player);
    }

    private void inspectPanel(Player player) {
        if (!isPanelFixed()){
            System.out.println("There are scorch marks around the edges of the panel.");
            if (!player.isItemInInventory("screwdriver")){
                System.out.println("Looks like you will need a screwdriver to get this panel open to see what's wrong.");
            } else {
                System.out.println("You use your screwdriver to pop the panel open and notice a broken wire.");
                if ((player.isItemInInventory("wire") || player.isItemInInventory("paper clip")) &&
                        (!player.isItemInInventory("soldering iron") && !player.isItemInInventory("bubble gum"))){
                    System.out.println("It looks like you have something that can be used as a wire,\n" +
                            "but you will need something to connect the wire to the contact points.");
                }else if ((!player.isItemInInventory("wire") && !player.isItemInInventory("paper clip")) &&
                        (player.isItemInInventory("soldering iron") || player.isItemInInventory("bubble gum"))){
                    System.out.println("It looks like you have something that can connect a wire to the contact points,\n" +
                            "now all you need is something to use as a wire");
                } else if (!player.isItemInInventory("wire") && !player.isItemInInventory("paper clip") &&
                        !player.isItemInInventory("soldering iron") && !player.isItemInInventory("bubble gum")){
                    System.out.println("You will need to find something that can be used as a wire and something to\n" +
                            "connect the wire to the contact points.");
                } else {
                    System.out.println("It looks like you have everything you need to fix the panel");
                    String answer = prompter.prompt("\nWould you like to repair the control panel? (y/n)",
                            "y|Y|n|N","Please select Y or N.").toLowerCase();
                    if ("y".equals(answer)){
                        String wire = player.isItemInInventory("wire") ? "wire":"paper clip";
                        String connector = player.isItemInInventory("soldering iron") ? "soldering iron":"bubble gum";
                        player.getItems().remove(wire);
                        player.getItems().remove(connector);
                        setPanelFixed(true);
                        System.out.println("You place the " + wire + " across the contact points and use the " +
                                connector + " to secure the wire in place.\n" +
                                "The lights on the control panel start to light up. Great job!" +
                                "\n Inspect the panel again to make sure it works.");
                    } else {
                        System.out.println("OK, but this will need to get fixed, sooner or later.");
                    }
                }
            }
        } else {
            if (!isEngineFixed){
                System.out.println("SYSTEM ERROR: ENGINE NEEDS TO BE FIXED BEFORE START UP SEQUENCE CAN BEGIN");
            } else {
                System.out.println("SYSTEM START-UP SEQUENCE COMPLETE - ALL SYSTEMS GO\n" +
                        "\nPRESS 1 TO WAKE THE PILOT\n" +
                        "PRESS 2 TO ACTIVATE SELF-DESTRUCT");
                String answer = prompter.prompt(">> ","[1-2]","INVALID SELECTION");
                switch (answer){
                    case "1":
                        System.out.println("CONGRATULATIONS YOU MADE IT OFF THE ALIEN PLANET");
                        TheCrashApp.setGameOver(true);
                        break;
                    case "2":
                        System.out.println("EVERYONE'S DEAD, ARE YOU HAPPY NOW?");
                        TheCrashApp.setGameOver(true);
                        break;
                }
            }
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private String itemChecker(String[] input) {
        String result = "null";
        for (String word: input) {
            if (items.containsKey(word)){
                result = items.get(word);
            }
        }
        return result;
    }

    private Map<String, String> loadItems() {
        JSONParser parser = new JSONParser();
        Map<String,String> result = new HashMap<>();
        try {
            Object obj = parser.parse(new FileReader(String.valueOf((Path.of("resources", "items.json")))));
            JSONObject itemWords = (JSONObject) obj;
            result.putAll(itemWords);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void inspectGeneric(String item) {
        if ("bubble gum".equals(item)){
            System.out.println("It's " + item + ". Nothing too special about it.");
        }else {
            System.out.println("It's a " + item + ". Nothing too special about it.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectPods(){
        System.out.println("Which pod would you like to inspect?\n" +
                "\n1. Pilot\n" +
                "2. Doctor\n" +
                "3. Engineer\n" +
                "4. Cook\n" +
                "5. Weapon's Master\n");
        String answer = prompter.prompt(">> ","[1-5]", "Please select between 1 and 5.");
        if ("5".equals(answer)) {
            if (!isPodOpen()){
                System.out.println("It looks like the Weapons Master's pod was damaged during the crash\n" +
                        "His vitals don't look good, you should probably use the keypad to wake him.\n");
                String response = prompter.prompt("\nWould you like to enter the code to wake the Weapons Master? (y/n)",
                        "y|Y|n|N","Please select Y or N.").toLowerCase();
                if ("y".equals(response)){
                    while (true){
                        String answer1 = prompter.prompt("Enter code, or E to exit");
                        if ("E".equals(answer1) || "e".equals(answer1)){
                            break;
                        } else if ("UUDDLRLRBA".equals(answer1)){
                            setPodOpen(true);
                            System.out.println("The pod opens and the Weapons Master jolt's awake.\n" +
                                    "He clutches his chest and stumbles aft towards the Armory\n" +
                                    "You hear a beep, a click, and then a thud as the Weapon's Master\n" +
                                    "falls over, dead.");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("The pod is empty");
            }
        } else {
            switch (answer){
                case "1":
                    System.out.println("You see the Pilot, he looks healthy");
                    break;
                case "2":
                    System.out.println("You see the Doctor, he looks healthy\n" +
                            "You also notice the numbers: 954 written on his hand.");
                    break;
                case "3":
                    System.out.println("That's your pod, it's empty");
                    break;
                case "4":
                    System.out.println("You see the Cook, he looks healthy");
                    break;
            }
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectBoard(Player player){
        if (player.getCurrentRoom().getItems().contains("map")){
            System.out.println("There is a map pinned to the board. Would probably help to navigate the ship.");
        } else {
            System.out.println("You see an empty bulletin board.");
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    private void inspectLocker(Player player){
        if (!isPodOpen()){
            System.out.println("The weapon's locker has a biometric lock that will only open for the Weapon's Master");
        } else {
            if (player.getCurrentRoom().getItems().contains("gun")){
                System.out.println("You see a laser gun inside the locker");
            } else {
                System.out.println("The locker is empty");
            }
        }
        prompter.prompt("\nPress Enter to continue...");
    }

    public boolean isFryerEmpty() {
        return isFryerEmpty;
    }

    public void setFryerEmpty(boolean fryerEmpty) {
        isFryerEmpty = fryerEmpty;
    }

    public boolean isCabinetOpen() {
        return isCabinetOpen;
    }

    public void setCabinetOpen(boolean cabinetOpen) {
        isCabinetOpen = cabinetOpen;
    }

    public boolean isEngineFixed() {
        return isEngineFixed;
    }

    public void setEngineFixed(boolean engineFixed) {
        isEngineFixed = engineFixed;
    }

    public boolean isPanelFixed() {
        return isPanelFixed;
    }

    public void setPanelFixed(boolean panelFixed) {
        isPanelFixed = panelFixed;
    }

    public boolean isPodOpen() {
        return isPodOpen;
    }

    public void setPodOpen(boolean podOpen) {
        isPodOpen = podOpen;
    }
}