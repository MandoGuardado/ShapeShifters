package com.shapeshifters.thecrash.service;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RoomTest {

    @Test
    public void testStarboardExit_shouldReturnFalse() {
        Map<String, String> exits = new HashMap<>();
        exits.put("Aft", "Armory");
        exits.put("Port", "Mess Hall");
        exits.put("Forward", "Bridge");


        Room berthing = new Room("Berthing", exits);

        assertEquals(false, berthing.isExitAvailable("Starboard"));
    }

    @Test
    public void testAftExit_shouldReturnTrue() {
        Map<String, String> exits = new HashMap<>();
        exits.put("Aft", "Armory");
        exits.put("Port", "Mess Hall");
        exits.put("Forward", "Bridge");


        Room room = new Room("Berthing", exits);
        assertEquals(true, room.isExitAvailable("Aft"));
    }

    @Test
    public void testForwardExit_shouldReturnTrue() {
        Map<String, String> exits = new HashMap<>();
        exits.put("Aft", "Armory");
        exits.put("Port", "Mess Hall");
        exits.put("Forward", "Bridge");


        Room room = new Room("Berthing", exits);
        assertEquals(true, room.isExitAvailable("Forward"));
    }

    @Test
    public void testPortExit_shouldReturnTrue() {
        Map<String, String> exits = new HashMap<>();
        exits.put("Aft", "Armory");
        exits.put("Port", "Mess Hall");
        exits.put("Forward", "Bridge");


        Room room = new Room("Berthing", exits);
        assertEquals(true, room.isExitAvailable("Port"));
    }
}