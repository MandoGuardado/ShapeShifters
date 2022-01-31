package com.shapeshifters.thecrash.service;

import static org.junit.Assert.*;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerTest {

    @Test
    public void TestToStringMethod_shouldReturnStringRepresentationOfFieldValues() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("Jane", berthing);
        System.out.println(player);
        assertEquals("PlayerPlayer name = Jane', Damage received = 100, items collected = []", player.toString());
    }


    @Test
    public void testGetName_shouldReturnValuePassedIn() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertEquals("John", player.getName());
    }

    @Test
    public void testGetHitValue_ShouldReturnDefaultHitValue() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertEquals(100, player.getHit());
    }

    @Test
    public void testGetHitValue_ShouldReturnValueOfFiftyAfterUpdate() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        player.setHit(50);
        assertEquals(50, player.getHit());
    }

    @Test
    public void testItemSizeAtInstantiation_shouldReturnSizeOfZero() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertEquals(0, player.getItems().size());
    }

    @Test
    public void testItemsSizeAfterAddingValues_shouldReturnValueOfTwo() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");
        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        Collection<String> stringList = new ArrayList<>();
        stringList.add("John");
        stringList.add("Jane");
        player.setItems(stringList);
        assertEquals(2, player.getItems().size());
    }

    @Test
    public void testGoingFromBerthingToValidDirectionAft_ShouldReturnTrue() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");

        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertTrue(player.isDesiredDirectionValid("aft"));

    }

    @Test
    public void testGoingFromBerthingToValidDirectionForward_ShouldReturnTrue() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");

        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);

        assertTrue(player.isDesiredDirectionValid("forward"));

    }
    @Test
    public void testGoingFromBerthingToValidDirectionPort_ShouldReturnTrue() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");

        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertTrue(player.isDesiredDirectionValid("port"));
    }
    @Test
    public void testGoingFromBerthingToValidDirectionStarboard_ShouldReturnFalse() {
        Map<String, String> berthingExits = new HashMap<>();
        berthingExits.put("aft", "Armory");
        berthingExits.put("port", "Mess Hall");
        berthingExits.put("forward", "Bridge");

        Room berthing = new Room("Berthing","This is were everybody sleeps", berthingExits);
        Player player = new Player("John", berthing);
        assertFalse(player.isDesiredDirectionValid("Starboard"));
    }
    @Test
    public void testGoingFromEngineeringToStarboard_ShouldReturnFalse() {
        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");

        Room engineering = new Room("Engineering","This is were everybody sleeps", engineeringExits);
        Player player = new Player("John", engineering);
        assertFalse(player.isDesiredDirectionValid("Starboard"));
    }
    @Test
    public void testGoingFromEngineeringToAft_ShouldReturnFalse() {
        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");

        Room engineering = new Room("Engineering","This is were everybody sleeps", engineeringExits);
        Player player = new Player("John", engineering);
        assertFalse(player.isDesiredDirectionValid("aft"));
    }

    @Test
    public void testGoingFromEngineeringToPort_ShouldReturnFalse() {
        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");

        Room engineering = new Room("Engineering","This is were everybody sleeps", engineeringExits);
        Player player = new Player("John", engineering);
        assertFalse(player.isDesiredDirectionValid("port"));
    }

    @Test
    public void testGoingFromEngineeringToForward_ShouldReturnTrue() {
        Map<String, String> engineeringExits = new HashMap<>();
        engineeringExits.put("Forward1", "Armory");
        engineeringExits.put("Forward2", "Med Bay");

        Room engineering = new Room("Engineering","This is were everybody sleeps", engineeringExits);
        Player player = new Player("John", engineering);
        assertTrue(player.isDesiredDirectionValid("forward"));
    }

}