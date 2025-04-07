package org.example.baekjoon.step1;


import java.util.Arrays;

public class LeetCode_TwoSUM {
    public int[] twoSum(int[] nums, int target) {


        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCode_TwoSUM leetCodeTwoSUM = new LeetCode_TwoSUM();
        System.out.println(Arrays.toString(leetCodeTwoSUM.twoSum(new int[]{2, 7, 11, 15}, 26)));
    }
}
