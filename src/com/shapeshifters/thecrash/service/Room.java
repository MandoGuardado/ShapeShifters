package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Room {
    //Fields
    private String name;
    private String description;
    private Map<String, String> exits;


    //constructors
    public Room(String name, String description, Map<String, String> exits) {
        this.name = name;
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

    public String getName() {
        return name;
    }


    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
