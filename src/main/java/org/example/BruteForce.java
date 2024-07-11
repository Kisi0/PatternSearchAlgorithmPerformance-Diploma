package org.example;

// Brute Force
class BruteForce {
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) { // kontrolllon nese modeli nuk perputhet me paternin ne tekst
                    break; //mbaron kur ka perpuethshmeri
                }
            }
            if (j == m) return i; // Pattern found
        }
        return -1; // Pattern not found
    }
}