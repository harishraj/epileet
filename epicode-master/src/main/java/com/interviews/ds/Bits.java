package com.interviews.ds;

public class Bits {

    public static int missingNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; ++i) {
            ret = ret ^ i;
            ret = ret ^ nums[i];
        }

        ret = ret ^ nums.length;
        return ret;
    }

    public static void main(String[] args) {
        int ele = missingNumber(new int[]{1, 4, 2, 6, 0, 5});
        System.out.println(ele);
    }
}
