package com.shapeshifters.thecrash.service;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;

public class Player {
    //Fields
    private String name;
    private int hit =  100;
    private Collection<String> items = new ArrayList<>(10);

    //constructors
    public Player(String name) {
        setName(name);
    }


    //Business Methods

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


    //toString()

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "Player name = " + getName() + '\'' +
                ", Damage received = " + getHit() +
                ", items collected = " + getItems();
    }
}
