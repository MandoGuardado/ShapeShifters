package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Room {
    //Fields
    private String description;
    private Map<String, String> exits;

    //constructors
    public Room(String description, Map<String, String> exits) {
        setDescription(description);
        setExits(exits);
    }

    //Business Methods
    public boolean isExitAvailable(String direction){
        return exits.containsKey(direction);
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
