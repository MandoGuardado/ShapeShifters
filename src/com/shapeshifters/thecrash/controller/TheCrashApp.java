package com.shapeshifters.thecrash.controller;

import java.util.Scanner;

public class TheCrashApp {
    private boolean gameOver = false;
    private String currentRoom = "berthing";

    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS
    public void execute(){
        Scanner in = new Scanner(System.in);

        while (!isGameOver()){
            System.out.println("What would you like to do?");
            String[] response = in.nextLine().toLowerCase().split(" ");
            if ("look".equals(response[0])){
                look(response[1]);
            }
        }
    }

    private void look(String dir){
        switch (dir){
            case "forward":
                lookForward(currentRoom);
                break;
            case "aft":
                lookAft(currentRoom);
                break;
            case "port":
                lookPort(currentRoom);
                break;
            case "stbd":
            case "starboard":
                lookStbd(currentRoom);
                break;
            default:
                System.out.println("I don't understand which way you want to look");
        }
    }

    private void lookForward(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the forward bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the forward bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the forward bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the forward bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the forward bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the forward bulkhead in engineering");
                break;
        }
    }

    private void lookAft(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the aft bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the aft bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the aft bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the aft bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the aft bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the aft bulkhead in engineering");
                break;
        }
    }

    private void lookPort(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the port bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the port bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the port bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the port bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the port bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the port bulkhead in engineering");
                break;
        }
    }

    private void lookStbd(String room){
        switch (room){
            case "bridge":
                System.out.println("You see the starboard bulkhead in the bridge");
                break;
            case "berthing":
                System.out.println("You see the starboard bulkhead in the berthing");
                break;
            case "mess hall":
                System.out.println("You see the starboard bulkhead in the mess hall");
                break;
            case "armory":
                System.out.println("You see the starboard bulkhead in the armory");
                break;
            case "med bay":
                System.out.println("You see the starboard bulkhead in the med bay");
                break;
            case "engineering":
                System.out.println("You see the starboard bulkhead in engineering");
                break;
        }
    }


    //GETTERS AND SETTERS
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
}