package org.example;

import java.io.IOException;


public class PerformanceTestFile {
    private static final String BASE_FILE_PATH = "textfile"; // path

    public static void main(String[] args) {
        int[] lengths = {10000, 200000, 1000000, 5000000};
        String pattern = "algorithm"; //pattern

        // Generate text files of specified lengths
        generateTextFiles(lengths, pattern);

        // Perform performance testing for each text file
        for (int length : lengths) {
            String filePath = BASE_FILE_PATH + "_" + length + ".txt";
            try {
                String text = FileUtil.readFile(filePath);
                System.out.println("Testing file: " + filePath);

                measurePerformance(new BruteForce(), text, pattern, "Brute Force");
                measurePerformance(new KMP(), text, pattern, "Knuth-Morris-Pratt");
                measurePerformance(new BoyerMoore(pattern), text, pattern, "Boyer-Moore");
                measurePerformance(new RabinKarp(pattern), text, pattern, "Rabin-Karp");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateTextFiles(int[] lengths, String pattern) {
        for (int length : lengths) {
            String text = TextGenerator.generateRandomText(length - pattern.length()) + pattern;
            String filePath = BASE_FILE_PATH + "_" + length + ".txt";
            try {
                TextGenerator.writeTextToFile(text, filePath);
                System.out.println("Generated text file of length " + length + " at: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void measurePerformance(Object algorithm, String text, String pattern, String algorithmName) {
        long startTime = System.nanoTime();
        int position = -1;
        if (algorithm instanceof BruteForce) {
            position = ((BruteForce) algorithm).search(text, pattern);
        } else if (algorithm instanceof KMP) {
            position = ((KMP) algorithm).search(text, pattern);
        } else if (algorithm instanceof BoyerMoore) {
            position = ((BoyerMoore) algorithm).search(text);
        } else if (algorithm instanceof RabinKarp) {
            position = ((RabinKarp) algorithm).search(text);
        }
        long endTime = System.nanoTime();

        // Garbage collection to get accurate memory usage
        System.gc();
        long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);

        System.out.println(algorithmName + " - Time: " + (endTime - startTime) + " ns, Memory: " + memoryUsed + " MB, Position: " + position);
    }
}
