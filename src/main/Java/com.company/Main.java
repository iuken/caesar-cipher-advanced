package com.company;


import com.company.repository.SimpleFileRepository;
import com.company.service.SimpleDecryptionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        SimpleFileRepository sfr = new SimpleFileRepository();
        SimpleDecryptionService sds = new SimpleDecryptionService();
        String text = sfr.readFileFromResources("cipher_text.txt");

        int[] keys = new int[33];
        for (int i = 0; i < 33; i++) {
            keys[i] = i;
        }
        String[] regex = sfr.readArrayFromResources("ExcludingRegEx2.txt");
        keys = sds.FilterByRegex(text, regex, keys);

        if (keys.length == 1) {
            System.out.println("key " + keys[0] + " is remained");
            System.out.println(sds.DecryptString(text, keys[0]));
        } else if (keys.length == 0) {
            System.out.println("No keys remained. It seems something is wrong. Check your filters and run the program again");
        } else {
            System.out.println("filtered " + (33 - keys.length) + " keys:");
            for (int key : keys) {
                String result = sds.DecryptString(text, key).substring(0, 90);
                System.out.println("key " + key + ":\t" + result + "...");
            }
            System.out.println("Type the right key, or type \"exit\" and run the program again with another filter: ");

            boolean isInputCorrect = false;
            int key = -1;
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (!isInputCorrect) {
                try {
                    key = -1;
                    line = reader.readLine();
                    if (line.equalsIgnoreCase("exit")) {
                        break;
                    }
                    key = Integer.parseInt(line);
                    for (int k : keys) {
                        if (key == k) {
                            isInputCorrect = true;
                            break;
                        }
                    }
                    if (isInputCorrect){
                        break;
                    }
                    System.out.println("Type the correct key or \"exit\": ");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("Type the correct key or \"exit\": ");
                }
            }

            if (key != -1) {
                System.out.println(sds.DecryptString(text, key));
            }
        }
    }
}
