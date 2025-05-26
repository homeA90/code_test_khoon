package org.example.code;

import java.util.*;

/*
문제 : 문자열 배열을 받아 에너그램 단위로 그룹핑하라.
* 애너그램이란 일종의 언어유희로 문자를 재배열하여 다른 뜻을 가진 단어로 바꾸는 것을 말한다. 예를들어 문전박대 -> 대박전문 이런 식으로 바꾼는 말
* 다시 말하면 애너그램을 이루는 단어를 정렬하면 같은 값이 된다!!
입력 : [ "eat", "tea", "tan", "ate", "ant", "cat" ]
출력 : [
        [ "ate", "eat", "tea" ]
        [ "ant", "tan" ]
        [ "cat" ]
      ]
 */
public class LeetCode49 {

    public static List<List<String>> groupAnagrams(String[] str) {

        // 핵심1. 입력받은 값을 정렬하고 정렬한 값을 보관하기 위해 Map을 선언한다.
        // 솔직히..문제만 보고 어떤 형식으로 값을 저장해야할지 아직 감이 안오기 때문에 우선 핵심 문제를 먼저 해결하는게 나을것 같음
        Map<String, List<String>> results = new HashMap<>();

        // 핵심2. 애너그럼을 이루는 단어를 전체 정렬하면 값은 값이 되므로 입력받은 값을 순회하면서 전체 정렬한다.
        // string 배열이므로 char[]로 변환시켜 문자 하나하나로 나눈 다음 Array.sort로 정렬한다. sort의 기본값은 오름차순!
        // char[] 로 변환된 값을 다시 string 으로 변경한다.
        for (String s : str) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            // 핵심3. 여기서부터 이제 정렬한 값을 저장해야하는데. 이때 어떤 형식으로 저장해야할지 선택해야한다.
            // 지금 문제에선 애너그럼 단어를 재정렬하면 모든 단어가 같은 값이 되는 특성이 있으므로 Map을 사용하여 정렬한 값을 key로 원본값을 value로 저장하면 좋을꺼같다!
            // 그래서 선언한 results에 key가 포함되어있지 않다면, 새롭게 [key, List] 배열을 만들어준다!
            if (!results.containsKey(key)) {
                results.put(key, new ArrayList<>());
            }
            // 반대로 results에 key가 포함되어 있다면, results 에서 key로 값을 불러와 원본값을 value에 저장한다.
            results.get(key).add(s);
            // 그렇게 된다면 결과는 [ate, ["eat", "tea", "ate" ]] 이런 식으로 저장될 것임!!
        }

        // 핵심4. 마지막으로 저장된 results를 문제의 반환타입에 맞게 입력한다! List<List<String>>으로 반환타입이 되어있으므로
        // new ArrayList<>() 처럼 List 배열을 만들어 return 하면 된다!
        return new ArrayList<>(results.values());
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "ant", "cat"};
        List<List<String>> results = groupAnagrams(str);
        System.out.println(results);
    }
}
