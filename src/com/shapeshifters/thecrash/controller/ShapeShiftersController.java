package com.shapeshifters.thecrash.controller;

import com.apps.util.Console;
import com.shapeshifters.thecrash.service.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class ShapeShiftersController {
    private static final int NUMBER_OF_QUESTIONS = 3;
    private static final Scanner in = new Scanner(System.in);
    private List<Map<String, String>> questions;
    /**
    * Constructor is used to call in setUp() method
    * */
    public ShapeShiftersController() {
        setUp();
    }

    /**
     * Controller method that controls the flow of an encounter with a ShapeShifter
     * @param player Player object representing current player
     * @param item item value that has been predetermined to be a ShapeShifter
     * @return boolean value to represent if the player won the encounter with ShapeShifter
     */
    public boolean encounterShapeShifter(Player player, String item) {
        boolean didPlayerWin =false;
        boolean isShapeShifterDead =false;
        Console.clear();
        System.out.println("\n\nYou have encountered a ShapeShifter...\n\n");
        System.out.println("In order to obtain the item you must defeat the ShapeShifter!\n\n");
        printBanner("alien");
        promptEnterKey();
        boolean isEncounterOver = false;
        int correctAnswer = 0;
        int numberOfQuestion = 0;
        if (player.isItemInInventory("gun")){
            System.out.println("You have a gun in your inventory do you want to use it against the ShapeShifter?");
            String response = in.nextLine().toLowerCase();
            if ("y".equals(response) || "yes".equals(response)){
                System.out.println("Boom!!");
                printBanner("shooting");
                if (randomNumberGenerator(0,1) == 1){
                    System.out.println("You killed the alien!");
                    isShapeShifterDead = true;
                }else{
                    System.out.println("You missed and the ShapeShifter was able to get close enough to inflict some damage and has gotten away.");
                }
                isEncounterOver = true;
            }
            else{
                System.out.println("No worries, you can still defeat the ShapeShifter by answer the following questions.");
            }
        }

        while (!isEncounterOver) {
            if (numberOfQuestion < NUMBER_OF_QUESTIONS) {
                int randomIndex = randomNumberGenerator(0, getQuestions().size() - 1);
                Map<String, String> question = questions.get(randomIndex);
                System.out.println("\n\nQuestion: " + question.get("question"));
                System.out.println("What will it be True or False?");

                boolean isAnsweredCorrectly = false;
                while (!isAnsweredCorrectly){
                    String response = in.nextLine().toLowerCase();
                    String answer = "null";

                    if ("true".equals(response) || "t".equals(response)){
                        answer = "true";
                    }else if ("false".equals(response) || "f".equals(response)){
                        answer = "false";
                    }

                    if (!"null".equals(answer)){
                        questions.remove(randomIndex);
                        if (question.get("answer").equals(answer)) {
                            System.out.println("Nice Job! You are correct.");
                            correctAnswer++;
                        }
                        else{
                            System.out.println("Sorry wrong answer!");

                        }
                        numberOfQuestion++;
                        isAnsweredCorrectly =true;
                    }
                    else{
                        System.out.println("Invalid response. Enter valid response such as 'true (t)'  or 'false(f)'.");

                    }

                }

            } else {
                isEncounterOver = true;
            }
        }
        if (correctAnswer >= 2 || isShapeShifterDead){
            didPlayerWin =true;
            System.out.println("You won! You can now pick up the item.");

        }
        else {
            System.out.println("Nice effort, you failed to beat the ShapeShifter and the item has now disappeared. You will not be able to get item.");
            removeItemWhenShapeShifterWins(player, item);
            applyDamage(player);
        }

        return didPlayerWin;
    }

    /**
     * Method that removes an items from either Dropped items list or Room's inventory and items lists.
     * @param player Player object representing current player
     * @param item item object that will be removed from lists
     */
    private void removeItemWhenShapeShifterWins(Player player, String item){
        if (player.getCurrentRoom().isItemInRoomDroppedItems(item)){
            player.getCurrentRoom().getDroppedItems().remove(item);
        }else{
            player.getCurrentRoom().getInventory().remove(item);
            player.getCurrentRoom().getItems().remove(item);
        }

    }

    /**
     * If player does not win encounter against ShapeShifter then player will receive damage to their health.
     * @param player Player object representing current player
     */
    private void applyDamage(Player player) {
        System.out.println("Your health will decrease by 25%");
        player.applyDamage(25);
    }

    /**
     * Initializes Question field using JAVA.Simple library to read from external file.
     */
    private void setUp() {
        JSONParser jsonparser = new JSONParser();
        List<Map<String, String>> questionHolder = new ArrayList<>();

        try {
            FileReader reader = new FileReader(String.valueOf(Path.of("resources", "questions.json")));
            Object obj = jsonparser.parse(reader);
            JSONArray questionArray = (JSONArray) obj;

            for (Object o : questionArray) {
                JSONObject questionJSONObject = (JSONObject) o;
                Map<String, String> questionObject = new HashMap<>();
                questionObject.putAll(questionJSONObject);
                questionHolder.add(questionObject);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        setQuestions(questionHolder);
    }


    /**
     * Prompts user to press enter key to continue
     */
    private static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print external text file
     * @param banner Name of text file without extension
     */
    private static void printBanner(String banner) {
        String fileName = banner.replaceAll("\\s", "").toLowerCase();
        Console.clear();
        Console.blankLines(2);
        try {
            Files.lines(Path.of("resources", fileName + ".txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to generates a random int between min and max (inclusive)
     * @param min Minimum value of range
     * @param max Max value of range
     * @return int value representing random number
     */
    public int randomNumberGenerator(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    // Setters and getters
    public List<Map<String, String>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Map<String, String>> questions) {
        this.questions = questions;
    }

}