package com.shapeshifters.thecrash.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    //Fields
    private String name;
    private String description;
    private Map<String, String> exits;
    private Map<String, String> views;
    private Collection<String> items;
    private Collection<String> droppedItems;


    //constructors
    public Room(String name, String description, Map<String, String> exits, Map<String, String> views, Collection<String> items) {
        this.name = name;
        setDescription(description);
        setExits(exits);
        setViews(views);
        setItems(items);
    }

    //Business Methods
    public boolean isExitAvailable(String direction){
        return exits.containsKey(direction);
    }

    public String getRoomView(String side){
        return getViews().get(side);
    }

    public boolean isItemInRoomInventory(String item){
        return getItems().contains(item);
    }
    public boolean isItemInDroppedRoomInventory(String item){
       return getDroppedItems().contains(item);
    }

    public void addToDroppedItemInventory(String item){
        getDroppedItems().add(item);
    }

    public String removedItemDroppedInv(String item){
        String message ="Item has been picked up from the floor.";
        getDroppedItems().remove(item);
        return message;
    }
    public String removedItemInv(String item){
        String message ="Item has been picked up.";
        getItems().remove(item);
        return message;
    }






    //Setter and getters
    public Map<String, String> getExits() {
        return exits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExits(Map<String, String> exits) {
        this.exits = exits;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getViews() {
        return views;
    }

    public void setViews(Map<String, String> views) {
        this.views = views;
    }

    public Collection<String> getItems() {
        return items;
    }

    public void setItems(Collection<String> items) {
        this.items = items;
    }

    public Collection<String> getDroppedItems() {
        return droppedItems;
    }

    public void setDroppedItems(Collection<String> droppedItems) {
        this.droppedItems = droppedItems;
    }

    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
