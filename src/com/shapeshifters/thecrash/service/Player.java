package com.shapeshifters.thecrash.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Player {
    //Fields
    private static final int MaxItemInventorySize = 5;
    private String name;
    private long health;
    private Collection<String> items = new ArrayList<>(5);
    private Room currentRoom;
    private final Scanner in = new Scanner(System.in);


    //constructors
    public Player(String name, Room startingRoom, long health, ArrayList<String> itemsList) {
        setName(name);
        setCurrentRoom(startingRoom);
        setHealth(health);
        setItems(itemsList);
    }


    //Business Methods

    /**
     * Determines if the direction player want to travel to valid (if door exits)
     * @param desiredDirection Direction value such as 'aft', 'forward', 'port', 'starboard
     * @return boolean representing if direction is valid
     */
    public boolean isDesiredDirectionValid(String desiredDirection) {
        boolean result = false;
        String roomName = getCurrentRoom().getName();
        if (("Bridge".equals(roomName) && "aft".equals(desiredDirection)) || ("Engineering".equals(roomName) && "forward".equals(desiredDirection)) || getCurrentRoom().isExitAvailable(desiredDirection)) {
            result = true;
        }
        return result;
    }

    /**
     * Returns a String description of the desired side based on the current room the player is in.
     * @param wall Name of side player want to see (aft, forward, port, starboard).
     * @return Description of the side player want to see based on the room it is in.
     */
    public String lookAt(String wall) {
        return getCurrentRoom().getRoomView(wall);
    }

    /**
     * Returns the value of the items in the current room where the player is at.
     * @return Collection of the items in the current room.
     */
    public Collection<String> getCurrentRoomItems() {
        return this.getCurrentRoom().getItems();
    }

    /**
     * Returns a boolean value determining if item is present in the items field
     * @param item Item String value
     * @return true: if found  false: if not found
     */
    public boolean isItemInInventory(String item) {
        return getItems().contains(item);
    }

    /**
     * Returns boolean if player cam still add another item to its inventory.
     * @return boolean if player is still under the limit, true: under limit, false: over limit
     */
    public boolean isItemSizeUnderLimit(){
        return getItems().size() < Player.MaxItemInventorySize;
    }

    /**
     * Returns boolean if item was added to the players inventory and checks if player is still under the limit
     * @param item Desired item to be added to players inventory
     * @return boolean value representing if item was added, true: added to inventory, false: not added to inventory
     */
    public boolean pickUpItem(String item) {
        boolean result = false;
        if (isItemSizeUnderLimit()){
            addToInventory(item);
            result = true;
        }
        return result;
    }

    /**
     * Adds to inventory
     * @param item Item that will be added to players inventory.
     */
    private void addToInventory(String item) {
        getItems().add(item);
    }

    /**
     * Public method that calls private method to remove item from players inventory
     * @param item Name of item that will be removed from players inventory.
     */
    public void dropItem(String item) {
        removeFromInventory(item);
    }

    /**
     * Private method that completes action of removing item from players inventory.
     * @param item Name of item that will be removed from players inventory
     */
    private void removeFromInventory(String item) {
        getItems().remove(item);
    }

    /**
     * Applies damage to players health
     * @param damage Value of deduction to the the players health
     */
    public void applyDamage(long damage){
        long updateHealth = getHealth() - damage;
        if (updateHealth < 0){
            setHealth(0);
        }
        else {
            setHealth(updateHealth);
        }
    }


    //Setter and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public Collection<String> getItems() {
        return items;
    }

    public void setItems(Collection<String> items) {
        this.items = items;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    //toString()

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "Player name = " + getName() + '\'' +
                ", Damage received = " + getHealth() +
                ", items collected = " + getItems();
    }
}
