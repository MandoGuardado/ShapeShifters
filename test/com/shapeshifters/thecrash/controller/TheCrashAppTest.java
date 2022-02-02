package com.shapeshifters.thecrash.controller;

import com.shapeshifters.thecrash.service.Player;
import com.shapeshifters.thecrash.service.Room;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TheCrashAppTest {

    @Test
    public void testGoMethodStartingAtBerthingAndGoingAft_ShouldReturnArmoryString() {
        String[] aft = new String[1];
        aft[0] = "aft";
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go(aft), "Armory");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingPort_ShouldReturnMessHallString() {
        String[] port = new String[1];
        port[0] = "port";
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go(port), "Mess Hall");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingForward_ShouldReturnArmoryString() {
        String[] forward = new String[1];
        forward[0] = "forward";
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go(forward), "Bridge");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingStarboard_ShouldReturnArmoryString() {
        String[] starboard = new String[1];
        starboard[0] = "starboard";
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go(starboard), "Berthing");
    }
}