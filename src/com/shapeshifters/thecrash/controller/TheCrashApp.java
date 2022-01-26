package com.shapeshifters.thecrash.controller;

public class TheCrashApp {
    private boolean gameOver = false;
    private String currentRoom;

    //NO-ARG CTOR
    public TheCrashApp() {
    }

    //BUSINESS METHODS
    public void execute(){
        while (!isGameOver()){

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