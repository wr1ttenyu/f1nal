package wr1ttenyu.f1nal.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        List<Character> chars = new ArrayList<>();
        String first = strs[0];
        String second = strs[1];

        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();
        for (int i = 0; i < (firstChars.length > secondChars.length ? secondChars.length : first.length()); i++) {
            if (firstChars[i] != secondChars[i]) break;
            chars.add(firstChars[i]);
        }

        if (chars.size() == 0) return "";

        for (int i = 2; i < strs.length; i++) {
            if (chars == null) break;
            if (strs[i].length() == 0) {
                chars = null;
                break;
            }
            char[] lastChars = strs[i].toCharArray();
            int j;
            for (j = 0; j < (lastChars.length > chars.size() ? chars.size() : lastChars.length); j++) {
                if (chars.get(j) == lastChars[j]) continue;
                if (j == 0) {
                    chars = null;
                }
                break;
            }
            if (chars != null) {
                chars = chars.subList(0, j);
            }
        }

        if (chars == null) return "";
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(c);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String[] test = {"aca", "cba"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(test));
    }
}
