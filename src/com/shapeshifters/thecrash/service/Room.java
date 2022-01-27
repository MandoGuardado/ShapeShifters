package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Room {
    //Fields
    private String description;
    Map<String, String> exits = new HashMap<>();



    //constructors


    public Room(String description, Map<String, String> exits) {
        this.description = description;
        this.exits = exits;
    }

    //Business Methods
    public boolean isExitAvailable(String direction){
        boolean result = false;
        if (exits.containsKey(direction)) {
            result = true;
        }
        return result;
    }

    //Setter and getters
    public Map<String, String> getExits() {
        return exits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExits(Map<String, String> exits) {
        this.exits = exits;
    }

    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
