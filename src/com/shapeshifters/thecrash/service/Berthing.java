package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Berthing {
    //Fields
    String description ="This is the Berthing (Will read off file)";
    Map<String, String> exits = new HashMap<>();



    //constructors
    public Berthing() {
        exits.put("Aft", "Armory");
        exits.put("Port", "Mess Hall");
        exits.put("Forward", "Bridge");

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

    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
