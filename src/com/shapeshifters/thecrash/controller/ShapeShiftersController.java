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
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

class ShapeShiftersController {
    private static final int NUMBER_OF_QUESTIONS = 3;
    private static final Scanner in = new Scanner(System.in);
    private List<Map<String, String>> questions;

    public ShapeShiftersController() {
        setUp();
    }

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
        if (player.isItemInInventory(item)){
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
            System.out.println("You must answer at least 2 of the following 3 questions.");
            if (numberOfQuestion < NUMBER_OF_QUESTIONS) {
                int randomIndex = randomNumberGenerator(0, getQuestions().size() - 1);
                Map<String, String> question = questions.get(randomIndex);
                questions.remove(randomIndex);
                System.out.println("Question: " + question.get("question"));
                System.out.println("What will it be True or False?");
                String response = in.nextLine().toLowerCase();
                if (question.get("answer").equals(response)) {
                    System.out.println("Nice Job! You are correct.");
                    correctAnswer++;
                }else{
                    System.out.println("Sorry wrong answer!");
                }
                numberOfQuestion++;
            } else {
                isEncounterOver = true;
            }
        }
        if (correctAnswer >= 2 || isShapeShifterDead){
            didPlayerWin =true;
            System.out.println("You won! You can now pick up the item.");;

        }
        else {
            System.out.println("Nice effort");
            applyDamage(player);
        }

        return didPlayerWin;
    }

    private void applyDamage(Player player) {
        player.applyDamage(25);
    }

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

    public List<Map<String, String>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Map<String, String>> questions) {
        this.questions = questions;
    }

    private static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

    public int randomNumberGenerator(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}