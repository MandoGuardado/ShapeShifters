package com.shapeshifters.thecrash.service;
import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class RoomTest {

    private static Room roomBerthing;
    private static Room roomBerthing2;
    private static Room roomEngineering;
    private static Room roomBridge;
    private static Room roomArmory;
    private static Room roomMessHall;
    private static Room roomMedBay;

    private static Map<String, Room> rooms;

    @BeforeClass
    public static void beforeClass() {

        Map<String, Room> setUpRoomsMap = new HashMap<>();

        try {
            JSONParser jsonparser = new JSONParser();
            FileReader reader = new FileReader(String.valueOf(Path.of("resources", "rooms.json")));
            Object obj = jsonparser.parse(reader);
            JSONArray roomArray = (JSONArray) obj;

            for (Object o : roomArray) {
                JSONObject roomJsonObject = (JSONObject) o;
                JSONObject exitsObject = (JSONObject) roomJsonObject.get("exits");
                JSONObject viewsObject = (JSONObject) roomJsonObject.get("views");
                JSONArray itemsArray = (JSONArray) roomJsonObject.get("items");
                Map<String, String> exits = new HashMap<>(exitsObject);
                Map<String, String> views = new HashMap<>(viewsObject);
                Collection<String> items = new ArrayList<>(itemsArray);
                setUpRoomsMap.put((String) roomJsonObject.get("name"), new Room((String) roomJsonObject.get("name"), (String) roomJsonObject.get("description"), exits, views, items));

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        rooms = setUpRoomsMap;
        roomBerthing = rooms.get("Berthing") ;
        roomEngineering = rooms.get("Engineering") ;
        roomBridge = rooms.get("Bridge");
        roomArmory = rooms.get("Armory") ;
        roomMessHall = rooms.get("Mess Hall") ;
        roomMedBay = rooms.get("Med Bay") ;
        roomBerthing2 = new Room(roomBerthing.getName(), roomBerthing.getDescription(), roomBerthing.getExits(), roomBerthing.getViews(), roomBerthing.getItems());

    }

    @Test
    public void testStarboardExit_shouldReturnFalse() {
        assertFalse(roomBerthing.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExit_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("aft"));
    }

    @Test
    public void testForwardExit_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("forward"));
    }

    @Test
    public void testPortExit_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("port"));
    }

    @Test
    public void testRandomNameExit_shouldReturnTrue() {
        assertFalse(roomBerthing.isExitAvailable("random"));

    }

    @Test
    public void testDescription_ShouldReturnAssignedDescription() {

        assertEquals("This is were everybody sleeps", roomBerthing.getDescription());
    }

    @Test
    public void testChangingDescription_ShouldUpdateDescriptionField() {

        roomBerthing2.setDescription("New Berthing description");
        assertEquals("New Berthing description", roomBerthing2.getDescription());
    }

}