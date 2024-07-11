package org.example;

public class RabinKarp {
    private final int R; // 256 karaktere
    private final long Q; // Prime modulus ne funskionin hash
    private final long RM; // R^(M-1) % Q per llogaritjen e hash
    private final long patHash;
    private final int m;
    private final String pattern;

    public RabinKarp(String pattern) {
        this.R = 256;
        this.m = pattern.length();
        this.pattern = pattern;
        this.Q = longRandomPrime();
        this.RM = calculateRM(m);
        this.patHash = hash(pattern, m);
    }

    private long calculateRM(int m) {
        long RM = 1;
        for (int i = 1; i <= m - 1; i++)
            RM = (R * RM) % Q;
        return RM;
    }

    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    public int search(String text) {
        int n = text.length();
        long txtHash = hash(text, m);
        if (patHash == txtHash && check(text, 0))
            return 0; // Pattern found
        for (int i = m; i < n; i++) {
            txtHash = (txtHash + Q - RM * text.charAt(i - m) % Q) % Q;
            txtHash = (txtHash * R + text.charAt(i)) % Q;
            if (patHash == txtHash && check(text, i - m + 1))
                return i - m + 1; // Pattern found
        }
        return -1; // Pattern not found
    }

    private boolean check(String text, int i) {
        for (int j = 0; j < m; j++)
            if (pattern.charAt(j) != text.charAt(i + j))
                return false;
        return true;
    }

    private static long longRandomPrime() {
        return 109345121; // Fixed prime for simplicity
    }
}
