package org.example.code;

import java.util.Scanner;

public class BJ11382 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] inputNum = input.split(" ");

        long a = Long.parseLong(inputNum[0]);
        long b = Long.parseLong(inputNum[1]);
        long c = Long.parseLong(inputNum[2]);


        System.out.println(a + b + c);
    }
}
