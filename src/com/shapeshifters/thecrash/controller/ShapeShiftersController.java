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

    public ShapeShiftersController() {
        setUp();
    }

    public boolean encounterShapeShifter() {
        boolean didPlayerWin =false;
        Console.clear();
        System.out.println("\n\nYou have encountered a ShapeShifter...\n\n");
        System.out.println("In order to surpass this obstacle you will need to answer the following questions correctly or face some damage!\n\n");
        printBanner("alien");
        promptEnterKey();
        boolean isEncounterOver = false;
        int correctAnswer = 0;
        int numberOfQuestion = 0;

        while (!isEncounterOver) {
            if (numberOfQuestion < NUMBER_OF_QUESTIONS) {
                int randomIndex = randomNumberGenerator();
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
        if (correctAnswer >= 2){
            didPlayerWin =true;
            System.out.println("You won! You have now picked up the item.");;

        }
        else {
            System.out.println("Nice effort, You lost and the ShapeShifter has disappeared");
//            applyDamage();
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

    public int randomNumberGenerator() {
        int min = 0;
        int max = getQuestions().size() - 1;

        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void main(String[] args) {
        ShapeShiftersController shapShifter = new ShapeShiftersController();
       boolean result = shapShifter.encounterShapeShifter();
        if(result){
            System.out.println("Great job you have won you beat the ShapeShifter");
        }
        else{
            System.out.println("Better luck next time..");
        }

    }
}