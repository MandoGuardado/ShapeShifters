package com.shapeshifters.thecrash.service;
import java.util.Scanner;

public class StartMenu {
        Scanner input = new Scanner(System.in);

            public void StartMainMenu(){
            int choice;
            do {
                System.out.println("Tʜᴇ Cʀᴀsʜ!\n" +
                        "Wʜᴏ's ᴅʀɪᴠɪɴɢ ᴛʜɪs ᴛʜɪɴɢ ᴀɴʏᴡᴀʏ?");
                System.out.println("==========================================");
                System.out.println("The Crash, Main Menu\n");
                System.out.print("1.) Start Game \n");
                System.out.print("2.) Instructions\n");
                System.out.print("3.) Exit\n");
                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        //call berthing to start game
                        break;

                    case 2:
                        System.out.println("The player can move left, right, east and west to explore\n" +
                                "different rooms in the ship. You have to find items by talking to different characters and help\n" +
                                "you repair the ship! ");
                        break;

                    case 3:
                        System.out.println("Exiting Program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(choice + " is not a valid Menu Option! Please Select Another.");

                }
            }
            while (choice != 4 /*Exit loop when choice is 4*/);
        }
    }



