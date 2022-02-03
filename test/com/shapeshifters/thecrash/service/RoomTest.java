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
    public void testStarboardExitFromBerthing_shouldReturnFalse() {
        assertFalse(roomBerthing.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExitFromBerthing_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("aft"));
    }

    @Test
    public void testForwardExitFromBerthing_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("forward"));
    }

    @Test
    public void testPortExitFromBerthing_shouldReturnTrue() {
        assertTrue(roomBerthing.isExitAvailable("port"));
    }
    @Test
    public void testStarboardExitFromEngineering_shouldReturnFalse() {
        assertFalse(roomEngineering.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExitFromEngineering_shouldReturnFalse() {
        assertFalse(roomEngineering.isExitAvailable("aft"));
    }

    @Test
    public void testForward1ExitFromEngineering_shouldReturnTrue() {
        assertTrue(roomEngineering.isExitAvailable("Forward1"));
    }
    @Test
    public void testForward2ExitFromEngineering_shouldReturnTrue() {
        assertTrue(roomEngineering.isExitAvailable("Forward2"));
    }

    @Test
    public void testPortExitFromEngineering_shouldReturnFalse() {
        assertFalse(roomEngineering.isExitAvailable("port"));
    }
    //
    @Test
    public void testStarboardExitFromArmory_shouldReturnFalse() {
        assertFalse(roomArmory.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExitFromArmory_shouldReturnTrue() {
        assertTrue(roomArmory.isExitAvailable("aft"));
    }

    @Test
    public void testForwardExitFromArmory_shouldReturnTrue() {
        assertTrue(roomArmory.isExitAvailable("forward"));
    }

    @Test
    public void testPortExitFromArmory_shouldReturnTrue() {
        assertTrue(roomArmory.isExitAvailable("port"));
    }
    //
    @Test
    public void testStarboardExitFromMedBay_shouldReturnTrue() {
        assertTrue(roomMedBay.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExitFromMedBay_shouldReturnTrue() {
        assertTrue(roomMedBay.isExitAvailable("aft"));
    }

    @Test
    public void testForwardExitFromMedBay_shouldReturnTrue() {
        assertTrue(roomMedBay.isExitAvailable("forward"));
    }

    @Test
    public void testPortExitFromMedBay_shouldReturnFalse() {
        assertFalse(roomMedBay.isExitAvailable("port"));
    }
    //
    @Test
    public void testStarboardExitFromMessHall_shouldReturnTrue() {
        assertTrue(roomMessHall.isExitAvailable("starboard"));
    }

    @Test
    public void testAftExitFromMessHall_shouldReturnTrue() {
        assertTrue(roomMessHall.isExitAvailable("aft"));
    }

    @Test
    public void testForwardExitFromMessHall_shouldReturnTrue() {
        assertTrue(roomMessHall.isExitAvailable("forward"));
    }

    @Test
    public void testPortExitFromMessHall_shouldReturnFalse() {
        assertFalse(roomMessHall.isExitAvailable("port"));
    }
    //
    @Test
    public void testStarboardExitFromBridge_shouldReturnFalse() {
       assertFalse(roomBridge.isExitAvailable("starboard"));
    }

    @Test
    public void testAft1ExitFromBridge_shouldReturnTrue() {
        assertTrue(roomBridge.isExitAvailable("Aft1"));
    }
    @Test
    public void testAft2ExitFromBridge_shouldReturnTrue() {
        assertTrue(roomBridge.isExitAvailable("Aft2"));
    }

    @Test
    public void testForwardExitFromBridge_shouldReturnFalse() {
       assertFalse(roomBridge.isExitAvailable("forward"));
    }

    @Test
    public void testPortExitFromBridge_shouldReturnFalse() {
        assertFalse(roomBridge.isExitAvailable("port"));
    }

    @Test
    public void testGetRoomViewAftFromBridge_shouldReturnDescriptionString() {
        assertEquals(roomBridge.getRoomView("aft"), "You see a door that leads to the Berthing compartment and a door that leads to the Mess Hall." );
    }
    @Test
    public void testGetRoomViewForwardFromBridge_shouldReturnDescriptionString() {
        assertEquals(roomBridge.getRoomView("forward"), "You see a control panel with what loos like soot around the edges." );
    }
    @Test
    public void testGetRoomViewPortFromBridge_shouldReturnDescriptionString() {
        assertEquals(roomBridge.getRoomView("port"), "You see a window that looks out into a strange world." );
    }
    @Test
    public void testGetRoomViewStarboardFromBridge_shouldReturnDescriptionString() {
        assertEquals(roomBridge.getRoomView("stbd"), "You see a window that looks out into a strange world." );
    }


    @Test
    public void testGetRoomViewAftFromEngineering_shouldReturnDescriptionString() {
        assertEquals(roomEngineering.getRoomView("aft"), "You see the main reactor for the ship." );
    }
    @Test
    public void testGetRoomViewForwardFromEngineering_shouldReturnDescriptionString() {
        assertEquals(roomEngineering.getRoomView("forward"), "You see a door that leads to the Med Bay and a door that leads to the Armory.");
    }
    @Test
    public void testGetRoomViewPortFromEngineering_shouldReturnDescriptionString() {
        assertEquals(roomEngineering.getRoomView("port"), "You see the coolant tanks for the reactor and some liquid on the ground.");
    }
    @Test
    public void testGetRoomViewStarboardFromEngineering_shouldReturnDescriptionString() {
        assertEquals(roomEngineering.getRoomView("stbd"), "You see a toolbox." );
    }


    @Test
    public void testGetRoomViewAftFromMedBay_shouldReturnDescriptionString() {
        assertEquals(roomMedBay.getRoomView("aft"), "You see a door that leads to the Engineering compartment." );
    }
    @Test
    public void testGetRoomViewForwardFromMedBay_shouldReturnDescriptionString() {
        assertEquals(roomMedBay.getRoomView("forward"), "You see a door that leads to the Mess Hall.");
    }
    @Test
    public void testGetRoomViewPortFromMedBay_shouldReturnDescriptionString() {
        assertEquals(roomMedBay.getRoomView("port"), "You see a desk.");
    }
    @Test
    public void testGetRoomViewStarboardFromMedBay_shouldReturnDescriptionString() {
        assertEquals(roomMedBay.getRoomView("stbd"), "You see a door that leads to the Armory and a locked medical cabinet.");
    }

    @Test
    public void testGetRoomViewAftFromArmory_shouldReturnDescriptionString() {
        assertEquals(roomArmory.getRoomView("aft"), "You see a door that leads to the Engineering compartment." );
    }
    @Test
    public void testGetRoomViewForwardFromArmory_shouldReturnDescriptionString() {
        assertEquals(roomArmory.getRoomView("forward"),"You see a door that leads to the Berthing compartment." );
    }
    @Test
    public void testGetRoomViewPortFromArmory_shouldReturnDescriptionString() {
        assertEquals(roomArmory.getRoomView("port"), "You see a door that leads to the Med Bay and shelves that contain tools.");
    }
    @Test
    public void testGetRoomViewStarboardFromArmory_shouldReturnDescriptionString() {
        assertEquals(roomArmory.getRoomView("stbd"), "You see a locked weapons cage." );
    }


    @Test
    public void testGetRoomViewAftFromMessHall_shouldReturnDescriptionString() {
        assertEquals(roomMessHall.getRoomView("aft"),"You see a door that leads to the Med Bay."  );
    }
    @Test
    public void testGetRoomViewForwardFromMessHall_shouldReturnDescriptionString() {
        assertEquals(roomMessHall.getRoomView("forward"),"You see a door that leads to the Bridge and a rack of empty trays.");
    }
    @Test
    public void testGetRoomViewPortFromMessHall_shouldReturnDescriptionString() {
        assertEquals(roomMessHall.getRoomView("port"), "You see a stove with an empty pot on it and a fryer with used oil in it.");
    }
    @Test
    public void testGetRoomViewStarboardFromMessHall_shouldReturnDescriptionString() {
        assertEquals(roomMessHall.getRoomView("stbd"),"You see a door that leads to the Berthing compartment." );
    }


    @Test
    public void testGetRoomViewAftFromBerthing_shouldReturnDescriptionString() {
        assertEquals(roomBerthing.getRoomView("aft"),"You see a door that leads to the Armory and a bulletin board with a map of the ship attached to it.");
    }
    @Test
    public void testGetRoomViewForwardFromBerthing_shouldReturnDescriptionString() {
        assertEquals(roomBerthing.getRoomView("forward"),"You see a door that leads to the Bridge and a locker for storing the crew's gear.");
    }
    @Test
    public void testGetRoomViewPortFromBerthing_shouldReturnDescriptionString() {
        assertEquals(roomBerthing.getRoomView("port"),"You see a door that leads to the Mess Hall and beds for all of the crew.");
    }
    @Test
    public void testGetRoomViewStarboardFromBerthing_shouldReturnDescriptionString() {
        assertEquals(roomBerthing.getRoomView("stbd"),"You see cryo-pods for the crew and it looks like you are the only one awake.");
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