package com.shapeshifters.thecrash.service;

import static org.junit.Assert.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerTest {
    private static Player playerBerthing;
    private static Player playerEngineering;
    private static Player playerBridge;
    private static Player playerArmory;
    private static Player playerMessHall;
    private static Player playerMedBay;

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
                Map<String, String> exits = new HashMap<>(exitsObject);
                Map<String, String> views = new HashMap<>(viewsObject);
                setUpRoomsMap.put((String) roomJsonObject.get("name"), new Room((String) roomJsonObject.get("name"), (String) roomJsonObject.get("description"), exits, views));

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        rooms = setUpRoomsMap;
        playerBerthing = new Player("Jane Berthing", rooms.get("Berthing"));
        playerEngineering = new Player("Jane Engineering", rooms.get("Engineering"));
        playerBridge = new Player("Jane Engineering", rooms.get("Bridge"));
        playerArmory = new Player("Jane Armory", rooms.get("Armory"));
        playerMessHall = new Player("Jane Mess Hall", rooms.get("Mess Hall"));
        playerMedBay = new Player("Jane Med Bay", rooms.get("Med Bay"));

    }


    @Test
    public void testToStringMethod_shouldReturnStringRepresentationOfFieldValues() {
        assertEquals("PlayerPlayer name = Jane Berthing', Damage received = 100, items collected = []", playerBerthing.toString());
    }


    @Test
    public void testGetName_shouldReturnValueJane() {
        assertEquals("Jane Berthing", playerBerthing.getName());
    }

    @Test
    public void testGetHitValue_ShouldReturnDefaultHitValueOf100() {
        assertEquals(100, playerBerthing.getHit());
    }

    @Test
    public void testGetHitValue_ShouldReturnValueOfFiftyAfterUpdate() {
        playerBerthing.setHit(50);
        assertEquals(50, playerBerthing.getHit());
    }

    @Test
    public void testItemSizeAtInstantiation_shouldReturnSizeOfZero() {
        assertEquals(0, playerBerthing.getItems().size());
    }

    @Test
    public void testItemsSizeAfterAddingValues_shouldReturnValueOfTwo() {
        Collection<String> stringList = new ArrayList<>();
        stringList.add("John");
        stringList.add("Jane");
        Player player2 = new Player("Jhon", rooms.get("Berthing"));
        player2.setItems(stringList);
        assertEquals(2, player2.getItems().size());
    }

    @Test
    public void testGoingFromBerthingToValidDirectionAft_ShouldReturnTrue() {
        assertTrue(playerBerthing.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testGoingFromBerthingToValidDirectionForward_ShouldReturnTrue() {
        assertTrue(playerBerthing.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromBerthingToValidDirectionPort_ShouldReturnTrue() {
        assertTrue(playerBerthing.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromBerthingToValidDirectionStarboard_ShouldReturnFalse() {
        assertFalse(playerBerthing.isDesiredDirectionValid("Starboard"));
    }

    @Test
    public void testGoingFromEngineeringToStarboard_ShouldReturnFalse() {
        assertFalse(playerEngineering.isDesiredDirectionValid("Starboard"));
    }

    @Test
    public void testGoingFromEngineeringToAft_ShouldReturnFalse() {
        assertFalse(playerEngineering.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testGoingFromEngineeringToPort_ShouldReturnFalse() {
        assertFalse(playerEngineering.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromEngineeringToForward_ShouldReturnTrue() {
        assertTrue(playerEngineering.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromBridgeToForward_ShouldReturnTrue() {
        assertFalse(playerBridge.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromBridgeToStarboard_ShouldReturnTrue() {
        assertFalse(playerBridge.isDesiredDirectionValid("starboard"));
    }

    @Test
    public void testGoingFromBridgeToPort_ShouldReturnTrue() {
        assertFalse(playerBridge.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromBridgeToAft_ShouldReturnTrue() {
        assertTrue(playerBridge.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testGoingFromMessHallToForward_ShouldReturnTrue() {
        assertTrue(playerMessHall.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromMessHallToStarboard_ShouldReturnTrue() {
        assertTrue(playerMessHall.isDesiredDirectionValid("starboard"));
    }

    @Test
    public void testGoingFromMessHallToPort_ShouldReturnTrue() {
        assertFalse(playerMessHall.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromMessHallToAft_ShouldReturnTrue() {
        assertTrue(playerMessHall.isDesiredDirectionValid("aft"));
    }


    @Test
    public void testGoingFromMedBayToForward_ShouldReturnTrue() {
        assertTrue(playerMedBay.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromMedBayToStarboard_ShouldReturnTrue() {
        assertTrue(playerMedBay.isDesiredDirectionValid("starboard"));
    }

    @Test
    public void testGoingFromMedBayToPort_ShouldReturnTrue() {
        assertFalse(playerMedBay.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromMedBayToAft_ShouldReturnTrue() {
        assertTrue(playerMedBay.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testGoingFromArmoryToForward_ShouldReturnTrue() {
        assertTrue(playerArmory.isDesiredDirectionValid("forward"));
    }

    @Test
    public void testGoingFromArmoryToStarboard_ShouldReturnTrue() {
        assertFalse(playerArmory.isDesiredDirectionValid("starboard"));
    }

    @Test
    public void testGoingFromArmoryToPort_ShouldReturnTrue() {
        assertTrue(playerArmory.isDesiredDirectionValid("port"));
    }
    @Test
    public void testGoingFromArmoryToAft_ShouldReturnTrue() {
        assertTrue(playerArmory.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testLookAtAftFromBridge_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Berthing compartment and a door that leads to the Mess Hall.", playerBridge.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromBridge_shouldReturnStringDescription() {
        assertEquals("You see a window that looks out into a strange world.", playerBridge.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromBridge_shouldReturnStringDescription() {
        assertEquals("You see a window that looks out into a strange world.", playerBridge.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromBridge_shouldReturnStringDescription() {
        assertEquals("You see a control panel with what loos like soot around the edges.", playerBridge.lookAt("forward") );
    }

    //
    @Test
    public void testLookAtAftFromEngineering_shouldReturnStringDescription() {
        assertEquals("You see the main reactor for the ship.", playerEngineering.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromEngineering_shouldReturnStringDescription() {
        assertEquals("You see a toolbox.", playerEngineering.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromEngineering_shouldReturnStringDescription() {
        assertEquals("You see the coolant tanks for the reactor and some liquid on the ground.", playerEngineering.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromEngineering_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Med Bay and a door that leads to the Armory.", playerEngineering.lookAt("forward") );
    }
    //
    @Test
    public void testLookAtAftFromMedBay_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Engineering compartment.", playerMedBay.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromMedBay_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Armory and a locked medical cabinet.", playerMedBay.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromMedBay_shouldReturnStringDescription() {
        assertEquals("You see a desk.", playerMedBay.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromMedBay_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Mess Hall.", playerMedBay.lookAt("forward") );
    }
    //
    @Test
    public void testLookAtAftFromArmory_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Engineering compartment.", playerArmory.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromArmory_shouldReturnStringDescription() {
        assertEquals("You see a locked weapons cage.", playerArmory.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromArmory_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Med Bay and shelves that contain tools.", playerArmory.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromArmory_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Berthing compartment.", playerArmory.lookAt("forward") );
    }

    //
    @Test
    public void testLookAtAftFromMessHall_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Med Bay.", playerMessHall.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromMessHall_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Berthing compartment.", playerMessHall.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromMessHall_shouldReturnStringDescription() {
        assertEquals("You see a stove with an empty pot on it and a fryer with used oil in it.", playerMessHall.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromMessHall_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Bridge and a rack of empty trays.", playerMessHall.lookAt("forward") );
    }
    //
    @Test
    public void testLookAtAftFromBerthing_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Armory and a bulletin board with a map of the ship attached to it.", playerBerthing.lookAt("aft") );
    }

    @Test
    public void testLookAtStarboardFromBerthing_shouldReturnStringDescription() {
        assertEquals("You see cryo-pods for the crew and it looks like you are the only one awake.", playerBerthing.lookAt("stbd") );
    }
    @Test
    public void testLookAtPortFromBerthing_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Mess Hall and beds for all of the crew.", playerBerthing.lookAt("port") );
    }

    @Test
    public void testLookAtForwardFromBerthing_shouldReturnStringDescription() {
        assertEquals("You see a door that leads to the Bridge and a locker for storing the crew's gear.", playerBerthing.lookAt("forward") );
    }
}

