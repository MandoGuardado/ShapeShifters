package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Room {
    //Fields
    private String name;
    private String description;
    private Map<String, String> exits;
    private Map<String, String> views;


    //constructors
    public Room(String name, String description, Map<String, String> exits, Map<String, String> views) {
        this.name = name;
        setDescription(description);
        setExits(exits);
        setViews(views);
    }

    //Business Methods
    public boolean isExitAvailable(String direction){
        return exits.containsKey(direction);
    }

    public String getRoomView(String side){
        return getViews().get(side);
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

    public Map<String, String> getViews() {
        return views;
    }

    public void setViews(Map<String, String> views) {
        this.views = views;
    }

    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
