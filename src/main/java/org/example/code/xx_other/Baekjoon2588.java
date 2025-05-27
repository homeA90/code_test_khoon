package org.example.code.xx_other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baekjoon2588 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();

        int a = scanner.nextInt();
        int b = scanner.nextInt();


        while (b > 0) {
            list.add(b % 10);
            b = b / 10;
        }

        int answer1 = a * list.get(0);
        int answer2 = a * list.get(1);
        int answer3 = a * list.get(2);
        int answer4 = answer1 + (answer2 * 10) + (answer3 * 100);

        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);

    }
}
