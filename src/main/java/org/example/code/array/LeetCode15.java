package org.example.code.array;

/*
문제 : 배열을 입력받아 합으로 0을 만들 수 있는 3개의 엘리먼트를 출력하라.
입력 : nums = [-1, 0, 1, 2, -1, -5]
출력 : [[-1, 0, 1], [-1, -1, 2]]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode15 {

    // 핵심! 세수의 합에 대한 접근 방식은 투포인트로 접근한다! 그런데 두수의 합과 다르게 1자리가 더 늘었다! 그래서 잘생각해야한다!
    // 포인트 하나를 기준점을 잡고 투포인트를 이용할것!!!! 이 부분 생각하기 쉽지 않음..
    // [1, 2, 3, 4] 가 있다면 1을 i로 기준 잡고 2,4 투포인트 잡고 풀면 됨!!! 두수의 합에 대한 응용이 필요!!!
    public static List<List<Integer>> threeSum(int[] nums) {
        // 투 포인트로 문제를 풀기위해 left, right 필드 선언!
        int left, right;

        // 반환 타입에 맞게 List 생성
        List<List<Integer>> elements = new ArrayList<>();

        // 해당 문제는 인덱스를 추출하는 문제가 아니므로 계산하기 쉽게 정렬하여 문제 풀기!!!!!!!
        // 단, 인덱스 값을 구하는 것이라면 투포인트로 하기 어려움! 한부분 기준 잡고 map을 이용하여 풀어야 할듯!
        Arrays.sort(nums);

        // 핵심1. 기준점이 전체 순회를 해야하므로 for 문 이용한다!
        // 여기서 length-2를 해준 이유는 left, right를 2개 사용하므로 -2가 필요함
        // for 문 바로 아래 if 문은 이전 값과 동일하다면 안해도 되니 continue로 pass 하는 코드
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // [1,2,3,4,5,6] 배열 존재 시 1(인덱스 0)이 기준이 되고 투포인트 지점은 left는 기준점 +1, right는  nums.length - 1 이 된다.
            // 그걸 선언해준것!
            left = i + 1;
            right = nums.length - 1;

            // 핵심2. while 문의 기준은 투포인트의 left가 right 보다 커지기 전까지 진행한다.
            while (left < right) {
                //  nums[i] + nums[left] + nums[right] 합이 0보다 작다면 숫자를 키워야 하므로 left 값을 키우고,
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                  //  nums[i] + nums[left] + nums[right] 합이 0보다 크다면 숫자를 줄여야 하므로 right 값을 줄인다.
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    // 모든 값에 해당이 되지 않으면  nums[i] + nums[left] + nums[right] 합이 0이므로 List인 elements 에 저장한다.
                    elements.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 이 부분은 합이 0이 후에 다음 값이 현재 값과 동일하다면 중복이므로 할필요가 없기 때문에 중복 정답을 피하기 위해 진행한다.
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 중복이 되지 않았다면 left와 right를 증가하여 다음 while 문을 진행시킨다!
                    left++;
                    right--;
                }
            }
        }
        // 그리고 최종적으로 element에 add에 저장했던 값을 return 하면 결과가 나온다.
        return elements;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -5};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }
}
