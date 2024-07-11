package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TextGenerator {
    public static String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a')); // Generate random lowercase letters
        }
        return sb.toString();
    }

    public static void writeTextToFile(String text, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(text);
        }
    }
}
