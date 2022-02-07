package com.shapeshifters.thecrash.service;

import java.util.*;

public class Room {
    //Fields
    private String name;
    private String description;
    private Map<String, String> exits;
    private Map<String, String> views;
    private Collection<String> items;
    private Collection<String> droppedItems = new ArrayList<>();
    private Collection<String > inventory;


    //constructors
    public Room(String name, String description, Map<String, String> exits, Map<String, String> views, Collection<String> items, Collection<String> inventory) {
        setName(name);
        setDescription(description);
        setExits(exits);
        setViews(views);
        setItems(items);
        setInventory(inventory);
    }

    //Business Methods

    /**
     * Return boolean value representing of desired direction is a valid exit
     * @param direction direction name
     * @return true: if valid exit, false: not an exit
     */
    public boolean isExitAvailable(String direction){
        return exits.containsKey(direction);
    }

    /**
     * Return the description view of the desired side from room
     * @param side name of side in room
     * @return String description of the desired side in room
     */
    public String getRoomView(String side){
        return getViews().get(side);
    }

    /**
     * Returns boolean value if item is found in the items field
     * @param item The item that is being searched.
     * @return true: item is found, false: item is not found
     */
    public boolean isItemInRoomItems(String item){
        return getItems().contains(item);
    }

    /**
     * Return boolean value representing if item was found inside inventory field
     * @param item name of item being searched.
     * @return true: item found, false: item not found
     */
    public boolean isItemInRoomInventory(String item){
        return getInventory().contains(item);
    }
    /**
     * Return boolean value representing if item was found inside dropped field
     * @param item name of item being searched.
     * @return true: item found, false: item not found
     */
    public boolean isItemInRoomDroppedItems(String item){
       return getDroppedItems().contains(item);
    }

    /**
     * Adds item to dropped inventory
     * @param item name of item being added
     */
    public void addToDroppedItemInventory(String item){
        getDroppedItems().add(item);
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

    public void setName(String name) {
        this.name = name;
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

    public Collection<String> getInventory() {
        return inventory;
    }

    public void setInventory(Collection<String> inventory) {
        this.inventory = inventory;
    }

    //toString()
    @Override
    public String toString() {
        return "Exit rooms available: " + getExits().values();
    }
}
