package org.example.baekjoon.step1;

/*
문제 : 문자열을 뒤집는 함수를 작성하라. 입력값은 문자 배열이며, 리턴 없이 입력 배열 내부를 직접 조작하라.
입력 : ["r","a","c","e","c","a","r"]
출력 : ["r","a","c","e","c","a","r"]
 */

import java.util.Arrays;

public class LeetCode_344 {
    public static void reverseStrings(String[] s) {
          int start = 0;
          int end =  s.length -1;

          while (start < end) {
             String temp = s[start];
             s[start] = s[end];
             s[end] = temp;
             start++;
             end--;
          }
    }

    public static void main(String[] args) {
        String[] test =  {"1","2","3","4","5","6","7"};
        reverseStrings(test);
        System.out.println(Arrays.toString(test));
    }
}
