package com.company;

import com.company.repository.SimpleFileRepository;
import com.company.service.SimpleDecryptionService;

public class Main {
    public static void main(String[] args) {
        SimpleFileRepository sfr = new SimpleFileRepository();
        SimpleDecryptionService sds = new SimpleDecryptionService();
        String text = sfr.readFileFromResources("cipher_text.txt");

        for (int i = 0; i < 33; i++) {
//            System.out.println(sds.DecryptString(text, i));
//            System.out.println();
        }

        int[] key = new int[33];
        for (int i = 0; i < 33; i++) {
            key[i] = i;
        }
        String[] regex = sfr.readArrayFromResources("ExcludingRegEx.txt");

        key = sds.FilterByRegex(text, regex, key);


        System.out.println(key.length);
        for (int i = 0; i < key.length; i++) {
            System.out.println(key[i]);
        }
//        System.out.println(regex[5]);


        for (int j = 0; j < regex.length; j++) {
            System.out.print(regex.length);
            System.out.print(regex[5]);
        }
    }
}
