package com.epi.backtracking;

import java.util.*;

public class PowerSet {
    // @include
    private static final double LOG_2 = Math.log(2);

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>> powerSet = new ArrayList<>();
        for (int intForSubset = 0; intForSubset < (1 << inputSet.size());
             ++intForSubset) {
            int bitArray = intForSubset;
            List<Integer> subset = new ArrayList<>();
            while (bitArray != 0) {
                subset.add(
                        inputSet.get((int) (Math.log(bitArray & ~(bitArray - 1)) / LOG_2)));
                bitArray &= bitArray - 1;
            }
            powerSet.add(subset);
        }
        return powerSet;
    }
    // @exclude

    private static void check(List<Integer> input, List<List<Integer>> expected) {
        List<List<Integer>> got = generatePowerSet(input);
        Set<Set<Integer>> gotSet = new HashSet<>();
        for (List<Integer> item : got) {
            gotSet.add(new HashSet<>(item));
        }
        if (gotSet.size() != got.size()) {
            System.err.println("Error, your powerset has duplicate entries.");
            System.err.println("Input:" + input);
            System.err.println("Your result:" + got);
            System.exit(-1);
        }

        Set<Set<Integer>> expectedSet = new HashSet<>();
        for (List<Integer> item : expected) {
            expectedSet.add(new HashSet<>(item));
        }

        if (!expectedSet.equals(gotSet)) {
            System.err.println("Error on input " + input);
            System.err.println("Expected " + expected);
            System.err.println("Got " + got);
            System.exit(-1);
        }
    }

    private static void simpleTest() {
        List<Integer> EMPTY_LIST = new ArrayList<>();

        List<Integer> input = Arrays.asList(0, 1, 2);
        List<List<Integer>> goldenResult = Arrays.asList(
                EMPTY_LIST, Arrays.asList(0), Arrays.asList(1), Arrays.asList(0, 1),
                Arrays.asList(2), Arrays.asList(0, 2), Arrays.asList(1, 2),
                Arrays.asList(0, 1, 2));

        check(input, goldenResult);

        input = EMPTY_LIST;
        goldenResult = Arrays.asList(EMPTY_LIST);
        check(input, goldenResult);

        input = Arrays.asList(Integer.MAX_VALUE);
        goldenResult = Arrays.asList(EMPTY_LIST, Arrays.asList(Integer.MAX_VALUE));
        check(input, goldenResult);
    }

    public static void main(String[] args) {
        simpleTest();
        List<Integer> S = new ArrayList<>();
        if (args.length >= 1) {
            for (String arg : args) {
                S.add(Integer.parseInt(arg));
            }
        } else {
            Random r = new Random();
            int count = r.nextInt(10) + 1;
            for (int i = 0; i < count; ++i) {
                S.add(i);
            }
        }
        List<List<Integer>> powerSet = generatePowerSet(S);
        for (List<Integer> oneSet : powerSet) {
            for (Integer ele : oneSet) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println("All tests passed.");
    }
}
