package com.shapeshifters.thecrash.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerTest {

    @Test
    public void TestToStringMethod_shouldReturnStringRepresentationOfFieldValues() {
        Player player = new Player("Jane");
        System.out.println(player);
        assertEquals("PlayerPlayer name = Jane', Damage received = 100, items collected = []", player.toString());
    }


    @Test
    public void testGetName() {
        Player player = new Player("John");
        assertEquals("John", player.getName());
    }

    @Test
    public void testGetHitValue() {
        Player player = new Player("John");
        assertEquals(100, player.getHit());
    }

    @Test
    public void testGetItemsCollectionLength() {
        Player player = new Player("John");
        assertEquals(0, player.getItems().size());
    }

    @Test
    public void testItemsSizeAfterAddingValues() {
        Player player = new Player("John");
        Collection<String> stringList = new ArrayList<>();
        stringList.add("John");
        stringList.add("Jane");
        player.setItems(stringList);
        assertEquals(2, player.getItems().size());
    }


}