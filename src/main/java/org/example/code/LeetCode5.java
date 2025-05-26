package org.example.code;

/*
문제 : 가장 긴 팰린드롬 부분 문자열을 출력하라.
입력 : dcbabcdd
출력 : dcbadcd
 */

public class LeetCode5 {

    // 해당 문제는 조금 처음이다보니 어렵게 느껴졌음!
    // 기본적인 인덱스와 길이에 대한 이해가 필요한 문제였음! 이 문제로 인덱스와 길이에 대한 이해가 조금 생겼음!!

    // 이 문제이 포인트는 문자열을 짝수면 2개, 홀수면 3개씩 묶어서 오른쪽으로 전진시킨 후 전진 시킨 상태에서 양쪽으로 팰린드롬인지 확인하도록 만드는 게 포인트다!
    // 여기서 묶어서 전진 시키는 걸 투 포인트 라고 하고, 묶인 투 포인트가 오른쪽으로 전진하는것을 슬라이딩 윈도우라고 한다.

    // 핵심1. 나중 출력을 위해 팰린드롬 첫번째 위치인 left와 팰린드롬의 최대 길이인 maxLen을 필드로 선언해 놓는다!
    static int left;
    static int maxLen;


    // 핵심2. 이 문제에선 양쪽으로 확산시켜 팰린드롬인지 확인하는 매서드와 투포인트를 이동시키는 매서드를 나눠서 구현하도록한다.
    // 아래 매서드는 투포인트로 이동된 값을 받아 해당값이 팰린드롬인지 확인하고 while을 통해 더 확장시켜 팰릭드롬인지 확인한다.
    // 그리고  기존의 maxLen(최대길이)와 비교하여 값이 크다면 현재 값을 저장한다.
    public static void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }

        // 여기서 중요한 포인트는 left와 maxLen을 계산하는 방식이다! while 문에서 마지막으로 빠져나올땐 j--와 k++을 하고 빠져나온다.
        // 하지만 while 문에 빠져나올땐 팰린드롬이 아니였기 때문에 빠져나온거라 최종 팰린드롬의 값은 j--와 k--하기 이전의 값이다
        // 그런데 이미 j--와 k++ 되었으므로 실제 팰린드롬의 값은 k는 (k-1), j는 (j+1)이 팰린드롬의 값인 것이다!
        // 그래서 최종 maxLen 길이 계산은 (k-1) - (j+1) 이다! 하지만 maxLen은 인덱스를 구하는 것이 아닌 길이를 구하는 것이므로 +1을 해야한다.
        // 예를들면 for 문에서 i < s.length() -1 <--- 이부분과 동일하다. 실제 length는 인덱스와 차이가 나므로 -1해주는것처럼 지금은 길이를 구해야하기 때문에
        // +1 을 하여 maxLen을 구하는 것이다! 아래 식은 (k - 1) - (j + 1) + 1 이부분을 정리하면 저렇게 나오는것!!!
        if (maxLen < k - j - 1) {
            left = j + 1;
            maxLen = k - j - 1; // (k - 1) - (j + 1) + 1
        }
    }

    // 핵심3. 투포인트로 값을 만드는 부분이다. 이부분은 어려운 부분이 없다.
    // 문자열을 최대길이를 len 으로 받는다. 만약 문자열 길이가 1자리면 그 문자열을 리턴하도록한다.
    // for 문을 이용하여 문자열 인덱스 만큼 팰린드롬 확인을 하도록한다. 하나는 짝수 하나는 홀수쪽으로 만들어 확인한다.
    // for 문이 완료되면 substring을 이용하여 값을 출력한다.
    // substring은 시작부터 최대 길이의 직전값을 뽑아주는 것이다!
    public static String longestPalindrome(String s) {
        int len = s.length();

        if (len < 2) return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i + 1);
            extendPalindrome(s, i, i + 2);
        }

        // 또 여기서 문제점 한가지! 왜 left + maxLen을 하는가?
        // 다시 말하지만 maxLen은 인덱스가 아니라 길이였으므로 시작값과 길이 값을 더해야 원하는 값앞에서 자를수 있다.
        return s.substring(left, left + maxLen);

        // 인덱스:   0   1   2   3   4   5   6
        // 값:      a   b   c   d   e   f   g
        // left = 2 → 인덱스 2부터 시작 (c)
        // maxLen = 4 → 길이가 4개 (c, d, e, f)
        // 그러면 필요한 인덱스 범위는? → 2, 3, 4, 5
        // 그래서 substring(2, 6)이어야 함 → left + maxLen = 2 + 4 = 6
    }

    public static void main(String[] args) {
        String s = "dcbabcdd";
        String p = longestPalindrome(s);
        System.out.printf(p);
    }
}
