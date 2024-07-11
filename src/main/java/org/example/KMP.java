package org.example;
class KMP {
    public static int search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int[] lps = computeLPSArray(pattern);//longest prefix suffix, metod per te llogaritur vargun
        int i = 0, j = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) { //nese karakteret perputhen rrit te dybtreguesit
                i++;
                j++;
            }
            if (j == m) {
                return i - j; // Pattern found
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; //perdor info nga lps
                } else {
                    i++;
                }
            }
        }
        return -1; // Pattern not found
    }

    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;
        lps[0] = 0;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++; //caktohet lsp pastaj i
            } else {
                if (length != 0) {
                    length = lps[length - 1]; //ca kto length ne lps
                } else { //ose
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}