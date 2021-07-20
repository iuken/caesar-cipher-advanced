package com.company.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SimpleFileRepository implements FileRepository {

    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder line = new StringBuilder();
        int readBytes = -1;
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/main/resources/" + fileName),
                StandardCharsets.UTF_8)) {
            while ((readBytes = isr.read()) != -1) {
                line.append((char) readBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(line);
    }

    public String[] readArrayFromResources(String fileName) {
        StringBuilder line = new StringBuilder();
        int readBytes = -1;
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/main/resources/" + fileName),
                StandardCharsets.UTF_8)) {
            while ((readBytes = isr.read()) != -1) {
                line.append((char) readBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(line).split("\n");
    }

}
