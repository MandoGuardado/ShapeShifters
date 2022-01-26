package com.shapeshifters.thecrash.service;

import java.util.HashMap;
import java.util.Map;

public class Berthing {
    //Fields
    Map<String, String> adjRooms = new HashMap<>();


    //constructors

    public Berthing() {
        adjRooms.put("Starboard", "Empty");
        adjRooms.put("Aft", "Armory");
        adjRooms.put("Port", "Mess Hall");
        adjRooms.put("Forward", "Bridge");

    }


    //Business Methods
    public boolean isPathAvailable(String direction){
        boolean result = false;
        if (adjRooms.containsKey(direction)) {
            result = true;
        }
        return result;
    }

    //Setter and getters
    public Map<String, String> getAdjRooms() {
        return adjRooms;
    }

    //toString()

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "Adjacent rooms  =" + getAdjRooms().values();
    }
}
