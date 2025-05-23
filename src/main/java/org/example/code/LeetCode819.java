package org.example.code;

/*
문제 : 금지된 단어를 제외하고 가장 흔하게 등장하는 단어를 출력하라.
      대소문자를 구분하지 않으며, 구두점(마침표, 쉼표 등) 또한 무시한다.
입력 : paragraph = "Ross hit a ball, the hit BALL flew far away after it was hit"
      banned = [hit]
출력 : "ball"
 */

import java.util.*;

public class LeetCode819 {

    public static String mostCommonWord(String paragraph, String[] banned) {

        // 핵심1. banned 는 제외해야할 단어임. 그러므로 제외 할 때 편하게 하기 위해 Set 을 사용
        // set은 중복저장이 안되므로 contains 라는 비교 메서드가 존재함
        // Arrays.asList는 String 배열을 List로 변환해주는 것!
        Set<String> ban = new HashSet<>(Arrays.asList(banned));

        // 핵심2. 가장 많이 등장하는 단어를 출력하는 것이므로 ["단어", 갯수]로 표현하기 위해 Map을 사용
        // ** 여기서 중요한 포인트는 문제에대해서 읽고 어떻게 분리를 할지? 어떤 컬렉션 or 배열에 담을지 잘 결정해야 함
        Map<String, Integer> counts = new HashMap<>();

        // 핵심3. 이제 본격적인 문제 풀이 시작! 주어진 String을 구분해야함! 오로지 문자만 count 하므로 정규식과 replaceAll로 문자로 변경
        // \w+ 는 문자가 아닌 것을 의미함. 앞에 \ 이걸 더 붙여준 이유는 \이거 한번만 쓰면 인식을 못하므로 \ 한번더 입력해서 인식되도록 하는 목적!
        // 문제에 대소문자가 나눠져있으므로 toLowerCase 사용하여 소문자로 통일
        // 마지막으로 모든 빈칸 기준으로 단어를 쪼갬! 각각 단어를 비교하기 위해서!
        String[] word = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");

        // 핵심4. split한 단어를 for-each를 이용하여 단어비교 시작
        // 여기서 banned 를 Set 으로 변경한 이유가 나옴! if 문에서 제외할 단어를 contains를 이용하여 제외하고 시작!
        // 그리고 가장 중요한 getOrDefault() 메서드!
        // getOrDefault는 앞에 지정한 map table 에서 작성한 key 값이 있는지 확인하여 존재하면 key의 value를 return 하고 없으면 0을 return 함
        // 그런데 뒤에 +1을 해준이유는 문제가 갯수를 세는 문제이므로 map table에 없더라도 확인한 key는 1개가 있는것이므로 +1을 해주는 것!
        // 만약 map table에 존재한다면 존재하는 key의 value 값을 return 받아 +1을 하는것!! 그래서 map에 값이 증가함!
        for (String w : word) {
            if (!ban.contains(w)) {
                counts.put(w, counts.getOrDefault(w, 0) + 1);
            }
        }

        // 핵심5. 여기도 중요함!! Collections.max 최대값을 찾는 메서드!
        // 자세한 설명은 아래에 되어 있음
        return Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();

        /*
        마지막 코드에 설명!
        Collections.max(...)
        → 컬렉션에서 가장 큰 값을 찾아주는 메서드입니다.
        여기선 Map.Entry<String, Integer> 타입의 요소들 중 하나를 반환하게 됩니다.

        counts.entrySet()
        → Map인 counts의 모든 엔트리 (Map.Entry)들을 Set으로 반환합니다.
        즉, [key1=value1, key2=value2, ...] 형태의 집합을 만듭니다.

        Map.Entry.comparingByValue()
        → Entry들의 value를 기준으로 비교하는 Comparator를 생성합니다.
        → 이 Comparator는 value 기준으로 정렬했을 때 가장 큰 값을 판단하게 합니다.

        Collections.max(...)에 이 Comparator를 넘기면
        → value가 가장 큰 Entry<K, V> 하나가 선택됩니다.

        마지막 .getKey()
        → 가장 큰 value를 가진 entry에서 그에 대응되는 key를 가져옵니다.
         */
    }

    public static void main(String[] args) {

        String paragraph = "Ross hit a ball, the hit BALL flew far away after it was hit";
        String[] banned = {"hit"};

        String result = mostCommonWord(paragraph, banned);
        System.out.println(result);
    }
}
