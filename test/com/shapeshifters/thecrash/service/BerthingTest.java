package com.shapeshifters.thecrash.service;
import static org.junit.Assert.*;
import org.junit.Test;

public class BerthingTest{

    @Test
    public void testStarboardExit_shouldReturnFalse() {
        Berthing berthing = new Berthing();
        assertEquals(false, berthing.isExitAvailable("Starboard"));
    }

    @Test
    public void testAftExit_shouldReturnTrue() {
        Berthing berthing =  new Berthing();
        assertEquals(true, berthing.isExitAvailable("Aft"));
    }

    @Test
    public void testForwardExit_shouldReturnTrue() {
        Berthing berthing =  new Berthing();
        assertEquals(true, berthing.isExitAvailable("Forward"));
    }

    @Test
    public void testPortExit_shouldReturnTrue() {
        Berthing berthing =  new Berthing();
        assertEquals(true, berthing.isExitAvailable("Port"));
    }
}