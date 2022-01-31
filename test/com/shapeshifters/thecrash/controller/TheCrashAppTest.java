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
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go("aft"), "Armory");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingPort_ShouldReturnMessHallString() {
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go("port"), "Mess Hall");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingForward_ShouldReturnArmoryString() {
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go("forward"), "Bridge");
    }
    @Test
    public void testGoMethodStartingAtBerthingAndGoingStarboard_ShouldReturnArmoryString() {
        TheCrashApp app = new TheCrashApp();
        app.setUp();
        assertEquals(app.go("Starboard"), "Berthing");
    }
}