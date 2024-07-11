package org.example;

import java.util.Random;

public class PerformanceTest {
    public static void main(String[] args) {
        int[] lengths = {10000, 200000, 1000000, 5000000};
        String pattern = ""; // Simple pattern for testing
//cikel qe te marre te gjitha gjatetite e dhena dhe te gjeneroj algoritmit per gjetjene patternit
        for (int length : lengths) { // merr gjatesite nga array qe kemi krijuar me emrin lengths
            String text = TextGenerator.generateRandomText(length);

            // Insert the pattern at a random position in the text
            int positionToInsertPattern = new Random().nextInt(length - pattern.length()); //gjatesi pa paternin
            text = text.substring(0, positionToInsertPattern) + pattern + text.substring(positionToInsertPattern + pattern.length());

            System.out.println("Testing text length: " + length);

            measurePerformance(new BruteForce(), text, pattern, "Brute Force");
            measurePerformance(new KMP(), text, pattern, "Knuth-Morris-Pratt");
            measurePerformance(new BoyerMoore(pattern), text, pattern, "Boyer-Moore");
            measurePerformance(new RabinKarp(pattern), text, pattern, "Rabin-Karp");

            System.out.println();
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
        long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        System.out.println(algorithmName + " - Time: " + (endTime - startTime) + " ns, Memory: " + memoryUsed + " MB, Position: " + position);
    }

}
