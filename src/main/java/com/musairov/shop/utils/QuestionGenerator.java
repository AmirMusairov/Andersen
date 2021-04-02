package com.musairov.shop.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class QuestionGenerator {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getStringAnswer(String question) {
        String answer = "-1";
        System.out.println(question);
        try {
            answer = reader.readLine().trim();
        } catch (Exception e) {
            System.out.println("Reader Error");
        }

        return answer;
    }

    public static Integer getIntAnswer(String question) {
        int answer = -1;
        System.out.println(question);
        try {
            answer = Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            System.out.println("Insertion Error");
        }

        return answer;
    }
}
