package com.interviews.misc;

// https://leetcode.com/problems/minimum-window-substring/
// https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems

import java.util.Set;

public class Substring {

    public String minWindow(String s, String t) {
        String result = "";
        if (s == "" || t.length() > s.length())
            return result;
        int[] map = new int[128];
        int start = 0;
        int minStart = 0;
        int end = 0;
        int count = t.length();
        int minLength = Integer.MAX_VALUE;
        for (char temp : t.toCharArray()) {
            map[temp]++;
        }
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0)
                count--;
            map[s.charAt(end)]--;
            end++;
            while (count == 0) {
                if (end - start < minLength) {
                    minStart = start;
                    minLength = end - start;
                }
                map[s.charAt(start)]++;
                if (map[s.charAt(start)] > 0)
                    count++;
                start++;
            }
        }
        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minStart, minStart + minLength);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int head = 0, i = 0;
        int[] sTable = new int[256];
        int repeat = 0;
        while (i < s.length()) {
            if (sTable[s.charAt(i++)]++ > 0) repeat++;   //total number of repeat
            while (repeat > 0) {
                if (sTable[s.charAt(head++)]-- > 1) repeat--;
            }
            len = Math.max(len, i - head);
        }
        return len;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int start = 0, end = 0, counter = 0;
        int[] map = new int[128];
        int max = Integer.MIN_VALUE;
        while (end < s.length()) {
            if (map[s.charAt(end)] == 0) {
                counter++;
            }
            map[s.charAt(end)]++;
            end++;
            while (counter > k) {
                map[s.charAt(start)]--;
                if (map[s.charAt(start)] == 0) {
                    counter--;
                }
                start++;
            }
            max = Math.max(max, end - start);
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    //https://leetcode.com/problems/one-edit-distance/

    /*
     * There're 3 possibilities to satisfy one edit distance apart:
     *
     * 1) Replace 1 char:
          s: a B c
          t: a D c
     * 2) Delete 1 char from s:
          s: a D  b c
          t: a    b c
     * 3) Delete 1 char from t
          s: a   b c
          t: a D b c
     */


    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length())
                    // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length())
                    // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }


    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (breakable[j] && dict.contains(s.substring(j, i))) {
                    breakable[i] = true;
                    break;
                }
            }
        }
        return breakable[s.length()];

    }
}


