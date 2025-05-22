package org.example.baekjoon.step1;

/*
문제 : 로그를 재정령하라. 기준은 다음과 같다
        1. 로그의 가장 앞부분은 식별자로서, 순서에 영향을 끼치지 않는다.
        2. 문자로 구성된 고르가 숫자 로그보다 앞에 오며, 문자 로그는 사전순으로 한다.
        3. 문자가 동일할 경우에는 식별자순으로 한다.
        4. 숫자 로그는 입력 순서대로 한다.
입력 : ["id1 8 1 5 1","id2 art can","id3 3 6","id4 own kit dig","id5 art zero"]
출력 : ["id2 art can","id5 art zero","id4 own kit dig","id1 8 1 5 1","id3 3 6"]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_937 {

    public static String[] reOrderData(String[] logs) {

        // 핵심1. 숫자와 문자를 List로 나눈다
        List<String> letterList = new ArrayList<>();
        List<String> digitList = new ArrayList<>();

        // 핵심2. 나누기 위해 빈칸을 기준으로 split 하고 우선 맨앞은 id는 우선적인 비교대상이 아니므로 두번째 인덱스를 추출하고, charAt 추출한 첫번째 문자열을 뽑아서
        // 숫자인지 문자인지 비교한 후 각 List에 담는다
        for(String log : logs) {
            if(Character.isDigit(log.split(" ")[1].charAt(0))) {
                digitList.add(log);
            } else {
                letterList.add(log);
            }
        }

        // 핵심3. 가장중요한 부분 문자열을 정렬하기 위해 sort를 이용하여 s1, s2를 비교한다.
        // compare 이용 같으면 0, s1을 앞자리에 오게하려면 -1(s1이 s2보다 작을 때 -1), s1을 뒷자리에 오게하려면 1(s1이 s2보다 클때 1)로 리턴
        // 하지만 해당문제에선 compare을 override 하지 않고 기본 compare을 이용하여 0이면 맨앞 id를 비교하여 위치시킨다.
        letterList.sort((s1, s2) ->{
            String[] s1x = s1.split(" ", 2); // 이부분은 split 으로 구분하는 갯수를 지정한다(ex, "1 2 3"  -> "1", "2 3" 이런식으로)
            String[] s2x = s2.split(" ", 2);

            int compare = s1x[1].compareTo(s2x[1]);
            if(compare == 0) {
                return s1x[0].compareTo(s2x[0]);
            } else {
                return compare;
            }
        });

        // 핵심4. 숫자는 입력순이므로 letterList에 그냥 추가한다. List의 추가방식은 맨뒤에 붙이는 것!
        letterList.addAll(digitList);

        // List를 String 배열로 바꿔야하므로 new String[0] 을 입력한다.
        // [0]을 입력해주는건 리스트의 크기에 맞춰 새로운 배열을 생성해주기 때문에 0을 입력한다.
        return letterList.toArray(new String[0]);

    }

    public static void main(String[] args) {
        String[] s = new String[]{"id1 8 1 5 1","id2 art can","id3 3 6","id4 own kit dig","id5 art zero"};
        System.out.println(Arrays.toString(reOrderData(s)));
    }
}
