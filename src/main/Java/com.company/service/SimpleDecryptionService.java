package com.company.service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDecryptionService implements DecryptionService {

    private static String upperCaseAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static String lowerCaseAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private char ShiftLetters(char letter, int key) {
        boolean isUpperCase = Character.isUpperCase(letter);
        int index = -1;
        if (!Character.isLetter(letter)) {
            return letter;
        }
        if (isUpperCase) {
            index = upperCaseAlphabet.indexOf(letter);
            letter = upperCaseAlphabet.charAt((index + key) % 33);
        } else {
            index = lowerCaseAlphabet.indexOf(letter);
            letter = lowerCaseAlphabet.charAt((index + key) % 33);
        }
        return letter;
    }

    public String DecryptString(String text, int key) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = this.ShiftLetters(chars[i], key);
        }
        return String.valueOf(chars);
    }

    public int[] FilterByRegex(String text, String[] regex, int[] keys) {
        String tempText;
        ArrayList<Integer> keyList = new ArrayList<Integer>();
        for (int i = 0; i < keys.length; i++) {
            keyList.add(keys[i]);
        }
        System.out.println(regex.length);
        for (int i = 0; i < keys.length; i++) {
            tempText = this.DecryptString(text, keys[i]);
            System.out.println("checking key: " + keys[i]);
            for (int j = 0; j < regex.length; j++) {
                Pattern pattern = Pattern.compile(regex[j]);
                System.out.print(regex[j] + " ");
                Matcher matcher = pattern.matcher(tempText);
                if (matcher.find()) {
                    System.out.println();
                    System.out.print("\"" + regex[j] + "\" was found: key " + keys[i] + " will be removed");
                    keyList.remove((Integer) keys[i]);
                    break;
                }
            }
            System.out.println();
            System.out.println();
        }
        int[] resultKey = new int[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            resultKey[i] = (int) keyList.get(i);
        }
        return resultKey;
    }
}