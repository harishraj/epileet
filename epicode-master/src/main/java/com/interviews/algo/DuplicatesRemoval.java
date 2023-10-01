package com.interviews.algo;

import java.util.Arrays;

public class DuplicatesRemoval {
    public static int[] removeDuplicates(int[] input) {
        Arrays.sort(input);
        int i = 0;
        int j = 1;

        while (j < input.length) {
            if (input[i] == input[j]) {
                j++;
            } else {
                i++;
                input[i] = input[j];
                j++;
            }
        }
        return Arrays.copyOf(input, i + 1);
    }

    public static void main(String[] args) {
        int[] input = {5,5,4,6,7,4,8,7,7,6,8};
        System.out.println(Arrays.toString(input));
        int[] out = removeDuplicates(input);
        System.out.println(Arrays.toString(out));
    }
}
