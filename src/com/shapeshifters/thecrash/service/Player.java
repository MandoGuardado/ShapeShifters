package com.shapeshifters.thecrash.service;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Player {
    //Fields
    private String name;
    private int hit =  100;
    private Collection<String> items = new ArrayList<>(10);
    private Room currentLocation;
    private Scanner in = new Scanner(System.in);


    //constructors
    public Player(String name, Room startingRoom) {
        setName(name);
        setCurrentLocation(startingRoom);
    }


    //Business Methods
    public String goToAdjacentRoom(String desiredDirection){
        String currentRoom = this.getCurrentLocation().getName();
        String result = currentRoom;
        if ("Bridge".equals(currentRoom) && "aft".equals(desiredDirection)){
            System.out.println("\nChoose 1 to go to Berthing\nChoose 2 to go to Mess Hall\n");
            int input = in.nextInt();
            switch (input){
                case 1:
                    result = "Berthing";
                    break;
                case 2:
                    result = "Mess Hall";
                    break;
            }
        }else if ("Engineering".equals(currentRoom) && "forward".equals(desiredDirection)){
            System.out.println("\nChoose 1 to go to Armory\nChoose 2 to go to Med Bay\n");
            int input = in.nextInt();
            switch (input){
                case 1:
                    result = "Armory";
                    break;
                case 2:
                    result = "Med Bay";
                    break;
            }
        } else if (getCurrentLocation().isExitAvailable(desiredDirection)){
            result =getCurrentLocation().getExits().get(desiredDirection);
        } else {
            System.out.println("You can't go in that direction");
        }
        return result;
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

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Room currentLocation) {
        this.currentLocation = currentLocation;
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
