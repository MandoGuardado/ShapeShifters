package com.shapeshifters.thecrash.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Status1 {
    public static void main(String[] args) throws IOException {
        String name = "Zrybea";
        int age = 30;
        ArrayList<String> favCities = new ArrayList<>(Arrays.asList("NYC", "Austin", "Munich"));
        String cities = "";
        for(String city : favCities){
            cities += (city + "\n");
        }


//        FileWriter stats = new FileWriter("resources/status.txt");

        List<String> lines = Files.readAllLines(Path.of("resources/status.txt"));

        String data = name + System.lineSeparator() + age + System.lineSeparator() + cities;

        for(int i = 0; i < lines.size(); i++){
            if(i == 1){
                lines.set(1, data);
            }
        }
        Files.write(Path.of("resources/status.txt"), lines);
    }
}
