package com.shapeshifters.thecrash.controller;

import com.shapeshifters.thecrash.service.Room;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class inspectController {
    private final Map<String,String> items;

    public inspectController() {
        this.items = loadItems();
    }

    public void inspect(String[] input, Collection<String> inv, String currentRoomString, Room currentRoom){
        String item = itemChecker(input);
        if (currentRoom.getItems().contains(item) || currentRoom.getDroppedItems().contains(item))
        switch (item){
            case "pods":
                inspectPods();
                break;
            case "locker":
                inspectLocker(currentRoom);
                break;
            case "board":
                inspectBoard(currentRoom);
                break;
            case "map":
                TheCrashApp.viewMap();
                break;
            case "panel":
                inspectPanel(currentRoom,inv);
            case "bed":
            case "gum":
                inspectGeneric(item);
                break;
        }
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

    }

    private void inspectPods(){

    }

    private void inspectBoard(Room currentRoom){

    }

    private void inspectLocker(Room currentRoom){

    }

}