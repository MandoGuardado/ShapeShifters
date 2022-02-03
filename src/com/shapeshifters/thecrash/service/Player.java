package com.shapeshifters.thecrash.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Player {
    //Fields
    private static final int MaxItemInventorySize = 5;
    private String name;
    private int hit = 100;
    private Collection<String> items = new ArrayList<>(5);
    private Room currentRoom;
    private Scanner in = new Scanner(System.in);


    //constructors
    public Player(String name, Room startingRoom) {
        setName(name);
        setCurrentRoom(startingRoom);
    }


    //Business Methods
    public boolean isDesiredDirectionValid(String desiredDirection) {
        boolean result = false;
        String roomName = getCurrentRoom().getName();
        if (("Bridge".equals(roomName) && "aft".equals(desiredDirection)) || ("Engineering".equals(roomName) && "forward".equals(desiredDirection)) || getCurrentRoom().isExitAvailable(desiredDirection)) {
            result = true;
        }
        return result;
    }

    public String lookAt(String wall) {
        return getCurrentRoom().getRoomView(wall);
    }

    public Collection<String> getCurrentRoomItems() {
        return this.getCurrentRoom().getItems();
    }

    public boolean isItemInInventory(String item) {
        return getItems().contains(item);
    }
    public boolean isItemSizeUnderLimit(){
        return getItems().size() < Player.MaxItemInventorySize;
    }

    public boolean pickUpItem(String item) {
        boolean result = false;
        if (isItemSizeUnderLimit()){
            addToInventory(item);
            result = true;
        }
        return result;
    }

    private void addToInventory(String item) {
        getItems().add(item);
    }


    public void dropItem(String item) {
        removeFromInventory(item);
    }

    private void removeFromInventory(String item) {
        getItems().remove(item);
    }


    //Setter and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
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
                ", Damage received = " + getHit() +
                ", items collected = " + getItems();
    }
}
