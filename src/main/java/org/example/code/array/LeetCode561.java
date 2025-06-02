package org.example.code.array;


import java.util.*;

/*
문제 : n개의 페어를 이용한 min(a,b)의 합으로 만들 수 있는 가장 큰 수를 출력하라.
입력 : [1, 3, 4, 2]
출력 : 4
설명 : n은 2개 되며, 최대합은 4다. min(1, 2) + min(3, 4) = 4
 */
public class LeetCode561 {

    // 해당 문제는 n개의 pair 즉, 2개의 값 중에 최소값을 선택하여 그 합을 더했을 때 가장 큰수를 출력하는 것이다.
    // 이 문제는 오름정렬을 해놓고 순서대로 최소값을 비교하여 그값을 더하면 합이 가장 큰수가 된다.
    // 1, 5, 9, 13 있다고 칠 때 2개씩 비교하여 최소값을 뽑는다면 1,5에서 1을 뽑고 9, 13에서 9를 뽑았을 때 그 합이 가장 큰수가 된다.
    public static int arrayPairSum1(int[] nums) {
        // 최소값을 합칠 변수 선언
        int sum = 0;

        // 매개값으로 받은 int 배열을 담을 List 객체 생성
        List<Integer> pair =  new ArrayList<>();

        // 해당 문제는 오름차순으로 하여 순서대로 비교하면 되므로 오름차순으로 정렬한다.
        Arrays.sort(nums);

        // int 배열을 전체 순회하며 pair 에 저장 후 pair에 값이 2개가 들어간다면, 2개 중 최소값을 선택해 sum에 더한다.
        // 그리고 다음 값을 비교하고 sum에 더학 위해 pair를 clear에 해서 값을 초기화 한다.
        for (int num : nums) {
            pair.add(num);
            if (pair.size() == 2) {
                sum += Collections.min(pair);
                pair.clear();
            }
        }
        return sum;
    }

    // 이번 풀이는 좀더 간결하게 할수 있다. 어쨋든 pair로 값을 비교해야하므로 2개씩 비교해야한다. 그런데 int 배열을 오름차순으로 정렬하면
    // 무조건 짝수 인덱스에 최소값이 들어간다! 그걸 이용하여 짝수만 골라내어 sum에 더하면 합이 가장 큰수를 만들 수 있다.
    public static int arrayPairSum2(int[] nums) {

        int sum = 0;
        Arrays.sort(nums);

        // i를 하나씩 증가시켜 int 배열을 전체 순회하며, i를 2로 나눴을 때 나머지가 0이라면 짝수이고 짝수는 정렬시 작은값에 해당되므로 해당 값을
        // sum에 저장하게 되면 가장 큰수를 출력할 수 있다.
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 ==0) {
                sum += nums[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2};
        int result1 = arrayPairSum1(nums);
        int result2 = arrayPairSum2(nums);

        System.out.println("1번 풀이: " + result1);
        System.out.println("2번 풀이: " + result2);

    }
}
