package org.example.code;

// 문제
// 주어진 문자열이 팰린드롬인지 확인해라. 대소문자를 구분하지 않으며, 영숫자(영문자와 숫자)만을 대상으로 한다.
// 입력 : "Do geese see God?"
// 출력 : true

public class LeetCode125 {

    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isPalindrome("Do geese see God?"));

    }
}
