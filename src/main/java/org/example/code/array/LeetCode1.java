package org.example.code.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
문제 : 덧셈하여 타깃을 만들 수 있는 배열의 두 숫자 인덱스를 리턴하라.
입력 :
출력 :
 */

public class LeetCode1 {
    // 브루트포스방법! 풀이방법이지만 가장 비효울적인 방법!
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
        LeetCode1 leetCode1 = new LeetCode1();
        System.out.println(Arrays.toString(leetCode1.twoSum(new int[]{2, 7, 11, 15}, 26)));
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();

        // 핵심1. 여기서 핵심은 Int 배열에서 인덱스 번호를 출력하라고 했으니, map 에다 기존 key와 value를 바꾸어 저장을한다.
        // 그래서 해당값을 key로 찾았을 때 인덱스가 vaule로 나오도록하는게 포인트다!
        for (int i = 0; i < nums.length; i++ ) {
            // target-num[i] 의 값이 numsMap에 있다면 get을 이용하여 key로 value를 리턴하고
            // 없다면 기존 값은 key에, 인덱스는 value에 넣어 저장한다.
            if (numsMap.containsKey(target - nums[i])) {
                return new int[]{numsMap.get(target - nums[i]), i};
            }
            numsMap.put(nums[i], i);
        }
        return null;
    }

    // 투 포인트로 문제 풀이
    public int[] twoSum3(int[] nums, int target) {

        // 단! 이 풀이 법에는 제약이 존재한다! 이 풀이법에 대한 전제는 전달받은 값은 무조건! 정렬을 시켜야한다.
        // 값을 찾으라는 문제는 얼마든지 해당 풀이법으로 할 수 있지만, 해당문제가 인덱스를 찾으라는 것이면 정렬 후 인덱스를 찾을 수 없다!

        // 투포인트로 문제를 풀려면 정렬이 되어있어야함! 하지만, 인덱스를 return 해야한다면 투 포인트로 문제풀기 힘듬!
        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;

        // 투 포인트를이용해서 start는 0, end는 배열끝에서 부터 시작!
        // 두값의 합이 target 보다 작으면 값을 증가시켜야 하므로 start의 값을 증가시킨다.
        // 반대로 두 값의 합이 target 보다 크면 값을 줄여야 하므로 end의 값을 감소시킨다.
        // 두개의 조건문에 일치하지 않으면 target과 같은 값이므로 해당 인덱스인 start와 end를 return 한다!

        while (start != end) {
            if (nums[start] + nums[end] < target) {
                start++;
            } else if (nums[start] + nums[end] > target) {
                end--;
            } else {
                return new int[]{start, end};
            }
        }
        return null;
    }
}
