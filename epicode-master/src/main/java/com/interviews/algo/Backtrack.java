package com.interviews.algo;

import java.util.*;

public class Backtrack {

    //****************************************************************************************************************
    // n Queens
    //****************************************************************************************************************

    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void solveNQueens(int n, int row, List<Integer> colPlacement,
                                     List<List<Integer>> result) {
        if (row == n) {
            // All queens are legally placed.
            result.add(new ArrayList<>(colPlacement));
        } else {
            for (int col = 0; col < n; ++col) {
                colPlacement.add(col);
                if (isValid(colPlacement)) {
                    solveNQueens(n, row + 1, colPlacement, result);
                }
                colPlacement.remove(colPlacement.size() - 1);
            }
        }
    }

    // Test if a newly placed queen will conflict any earlier queens placed before.
    private static boolean isValid(List<Integer> colPlacement) {
        int rowID = colPlacement.size() - 1;
        for (int i = 0; i < rowID; ++i) {
            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
            if (diff == 0 || diff == rowID - i) {
                return false;
            }
        }
        return true;
    }

    //****************************************************************************************************************
    // Word exists
    //****************************************************************************************************************

    public boolean wordExists(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (search(board, word, i, j, 0)) {
                        return true;
                    } else continue;
                }
            }
        }

        return false;
    }
    public static void main(String[] args) {

        Backtrack2 backtrackTemplate = new Backtrack2();

        char[] charA = {'a', 'c', 'e', 'd'};
        char[] charB = {'b', 'a', 'd', 'w'};
        char[] charC = {'e', 's', 'a', 'e'};

        char[][] charAB = {charA, charB, charC};

        boolean resu = backtrackTemplate.wordExists(charAB, "edas");
        System.out.println(resu);

    /*
        List<String> result = backtrackTemplate.generateParentheses(3);
        Iterator iter = result.iterator();

        while (iter.hasNext()) System.out.println(iter.next());

        List<List<String>> list = backtrackTemplate.partition("aabba");

        Iterator<List<String>> iter = list.iterator();
        while (iter.hasNext()) {
            List<String> innerList = iter.next();
            Iterator<String> innerIter = innerList.iterator();

            while (innerIter.hasNext()) System.out.println(innerIter.next());
        }



        List<List<Integer>> intList = new ArrayList<>();

        int[] intArray = {2,6,5};

        int[] intArray1 = {2,5,5};

        intList = backtrackTemplate.subsetsOfSizeK(intArray, 3);

        System.out.println("Combinations / Subsets of the set {2,6,5} of Size 3 ");

        Iterator<List<Integer>> intIter = intList.iterator();

        while (intIter.hasNext()) {
            List<Integer> innerList = intIter.next();
            System.out.println(Arrays.toString(innerList.toArray()));
        }

        intList = backtrackTemplate.subsetsWithDup(intArray1);

        System.out.println(" Subsets with duplicates of the set {2,5,5} ");

        intIter = intList.iterator();

        while (intIter.hasNext()) {
            List<Integer> innerList = intIter.next();
            System.out.println(Arrays.toString(innerList.toArray()));
        }

        System.out.println("Permutations of the set {2,6,5}");

        intList = backtrackTemplate.permutate(intArray);

        intIter = intList.iterator();

        while (intIter.hasNext()) {
            List<Integer> innerList = intIter.next();
            System.out.println(Arrays.toString(innerList.toArray()));
        }

        System.out.println("Permutations of the set {2,5,5}");

        intList = backtrackTemplate.permutateUnique(intArray1);

        intIter = intList.iterator();

        while (intIter.hasNext()) {
            List<Integer> innerList = intIter.next();
            System.out.println(Arrays.toString(innerList.toArray()));
        }
    */

    }

    /**
     * Pick a starting point.
     * while(Problem is not solved)
     * For each path from the starting point.
     * check if selected path is safe,
     * if yes select it
     * and make recursive call to rest of the problem
     * before which undo the current move.
     * End For
     * <p>
     * If none of the move works out, return false, NO SOLUTON.
     */

    //*****************************************************************************************
    // Subsets : https://leetcode.com/problems/subsets/
    // Combinations
    //*****************************************************************************************

    public List<List<Integer>> subsets(int[] array) {
        List<List<Integer>> list = new ArrayList<>();
        subsetBacktrack(list, new ArrayList<>(), array, 0);
        return list;
    }

    private void subsetBacktrack(List<List<Integer>> result, List<Integer> tempList, int[] array, int start) {

        result.add(new ArrayList<>(tempList));

        for (int i = start; i < array.length; i++) {

            tempList.add(array[i]);
            subsetBacktrack(result, tempList, array, i + 1);
            tempList.remove(tempList.size() - 1);

        }
    }

    //*****************************************************************************************
    // Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
    //*****************************************************************************************

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupBacktrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void subsetsWithDupBacktrack(List<List<Integer>> list, List<Integer> tempList,
                                         int[] nums, int start) {

        list.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {

            //    if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            if (i > start && nums[i] != nums[i - 1]) {
                tempList.add(nums[i]);
                subsetsWithDupBacktrack(list, tempList, nums, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //*****************************************************************************************
    // Generate all subsets of size k
    // Subsets : https://leetcode.com/problems/subsets/
    // Combinations
    //*****************************************************************************************

    public List<List<Integer>> subsetsOfSizeK(int[] array, int K) {
        List<List<Integer>> list = new ArrayList<>();
        subsetSizeKBacktrack(list, new ArrayList<>(), K, array, 0);
        return list;
    }

    private void subsetSizeKBacktrack(List<List<Integer>> result, List<Integer> tempList,
                                      int K, int[] array, int start) {

        if (tempList.size() == K) {
            result.add(new ArrayList<>(tempList));
        } else {

            for (int i = start; i < array.length; i++) {
                tempList.add(array[i]);
                subsetSizeKBacktrack(result, tempList, K, array, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //*****************************************************************************************
    // Permutations : https://leetcode.com/problems/permutations/
    //*****************************************************************************************

    public List<List<Integer>> permutate(int[] array) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        permutateBacktrack(list, new ArrayList<>(), array);
        return list;
    }

    private void permutateBacktrack(List<List<Integer>> result, List<Integer> tempList, int[] array) {

        if (tempList.size() == array.length) {
            result.add(new ArrayList<>(tempList));
        } else {

            for (int i = 0; i < array.length; i++) {

                if (tempList.contains(array[i])) continue; // element already exists, skip
                tempList.add(array[i]);
                permutateBacktrack(result, tempList, array);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //*****************************************************************************************
    // Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    // Combinations
    //*****************************************************************************************

    public List<List<Integer>> permutateUnique(int[] array) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(array);
        permutateUniqueBacktrack(list, new ArrayList<>(), array, new boolean[array.length]);
        return list;
    }

    private void permutateUniqueBacktrack(List<List<Integer>> result, List<Integer> tempList,
                                          int[] array, boolean[] used) {

        if (tempList.size() == array.length) {
            result.add(new ArrayList<>(tempList));
        } else {

            for (int i = 0; i < array.length; i++) {

                if (used[i] || i > 0 && array[i] == array[i - 1] && !used[i - 1]) continue;

                used[i] = true;
                tempList.add(array[i]);
                permutateUniqueBacktrack(result, tempList, array, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //*****************************************************************************************
    // Permutations : https://leetcode.com/problems/permutations/  Swap method
    //*****************************************************************************************

    public List<List<Integer>> permutateSwapMethod(Integer[] array) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> srcList = Arrays.asList(array);
        permutateSwapBacktrack(result, srcList, 0);
        return result;
    }

    private void permutateSwapBacktrack(List<List<Integer>> result, List<Integer> A, int i) {

        if (i == A.size() - 1) {
            result.add(new ArrayList<Integer>(A));
            return;
        } else {

            for (int j = i; j < A.size(); ++j) {

                Collections.swap(A, i, j);
                permutateSwapBacktrack(result, A, i + 1);
                Collections.swap(A, i, j);
            }
        }
    }

    //*****************************************************************************************
    // Combination Sum : https://leetcode.com/problems/combination-sum/
    //*****************************************************************************************

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        combinationSumBacktrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void combinationSumBacktrack(List<List<Integer>> result, List<Integer> tempList, int[] nums,
                                         int remain, int start) {

        if (remain < 0)
            return;
        else if (remain == 0)
            result.add(new ArrayList<>(tempList));

        else {

            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                combinationSumBacktrack(result, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //****************************************************************************************************************
    //Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    //****************************************************************************************************************

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        combinationSum2Backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void combinationSum2Backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int remain, int start) {

        if (remain < 0)
            return;
        else if (remain == 0)
            result.add(new ArrayList<>(tempList));

        else {

            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates

                tempList.add(nums[i]);
                combinationSum2Backtrack(result, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //****************************************************************************************************************
    // Generate Parentheses
    //****************************************************************************************************************

    public List<String> generateParentheses(int n) {

        List<String> result = new ArrayList<>();
        generateParen(n, n, result, "");
        return result;
    }

    public void generateParen(int left, int right, List<String> result, String temp) {

        if (left == 0 && right == 0)
            result.add(temp);

        if (left > 0) {
            generateParen(left - 1, right, result, temp + "(");
        }

        if (left < right) {
            generateParen(left, right - 1, result, temp + ")");
        }
    }

    //*****************************************************************************************
    // Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
    //*****************************************************************************************

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        partitionBacktrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void partitionBacktrack(List<List<String>> list, List<String> tempList, String s, int start) {

        if (start == s.length())
            list.add(new ArrayList<>(tempList));
        else {

            for (int i = start; i < s.length(); i++) {

                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    partitionBacktrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    //****************************************************************************************************************
    // Word search
    //****************************************************************************************************************

    boolean[][] visited;

    private boolean search(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        if (search(board, word, i - 1, j, index + 1) ||
                search(board, word, i + 1, j, index + 1) ||
                search(board, word, i, j - 1, index + 1) ||
                search(board, word, i, j + 1, index + 1)) {
            return true;
        }

        visited[i][j] = false;
        return false;
    }

    //****************************************************************************************************************
    // Word  pattern
    // https://leetcode.com/problems/word-pattern-ii/
    //****************************************************************************************************************

    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

    //****************************************************************************************************************
    // https://leetcode.com/problems/sudoku-solver/
    //****************************************************************************************************************

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        //trial. Try 1 through 9
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; //Put c for this cell

                            if (solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    // https://leetcode.com/problems/factor-combinations/
    // Write a function that takes an integer n and return all possible combinations of its factors.

    /*
    input: 32
        output:
        [
          [2, 16],
          [2, 2, 8],
          [2, 2, 2, 4],
          [2, 2, 2, 2, 2],
          [2, 4, 4],
          [4, 8]
        ]
     */

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false; //check row
            if (board[row][i] != '.' && board[row][i] == c) return false; //check column
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), n, 2);
        return result;
    }


    //*****************************************************************************************
    // Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    // Combinations
    //*****************************************************************************************


    //***********************************************************************************************
    //***********************************************************************************************
    //***********************************************************************************************
    //***********************************************************************************************
    //***********************************************************************************************

    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start) {
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<Integer>(item));
            }
            return;
        }

        for (int i = start; i <= n; ++i) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n / i, i);
                item.remove(item.size() - 1);
            }
        }
    }
}

