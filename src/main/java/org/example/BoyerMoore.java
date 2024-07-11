package org.example;

import java.util.Arrays;

// Boyer-Moore
class BoyerMoore {
    private final int R;
    private int[] right;
    private String pattern;

    public BoyerMoore(String pattern) {
        this.R = 256; //perfaqeson madhesine e alfabetit
        this.pattern = pattern;
        right = new int[R]; //varg qe permban madhesine R
        Arrays.fill(right, -1); //mbush vektorin me vlera qe nuk jane gjetur
        for (int j = 0; j < pattern.length(); j++) {
            right[pattern.charAt(j)] = j; //percakton karakteret deri tek fillimi i gjateise se patternit
        }
    }

    public int search(String text) {
        int m = pattern.length();
        int n = text.length();
        int skip; //per te mbajtur nr e pozicioneve te kaluara
        for (int i = 0; i <= n - m; i += skip) { //loop iteron derine pozicionin ku patterni pershtatet
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) { // kontrolllon nese modeli nuk perputhet me paternin ne tekst
                    skip = Math.max(1, j - right[text.charAt(i + j)]); //sa pozzicione kalohen
                    break; //mbaron deri sa te gjendet nje pattern i barabarte n etext
                }
            }
            if (skip == 0) return i; // Pattern found
        }
        return -1; // Pattern not found
    }
}