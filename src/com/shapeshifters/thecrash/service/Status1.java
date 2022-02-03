package com.shapeshifters.thecrash.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Status1 {
    public static void main(String[] args) throws IOException {
        String name = "Zrybea";
        int age = 30;
        ArrayList<String> favCities = new ArrayList<>(Arrays.asList("NYC", "Austin", "Munich"));


        String cities = "";

        for(String city : favCities){
            cities += (city + "\n");
        }

        FileWriter stats = new FileWriter("resources/status.txt");
        stats.write(name + System.lineSeparator() + age + System.lineSeparator() + cities);
        stats.close();
        System.out.println("done");
    }
}
