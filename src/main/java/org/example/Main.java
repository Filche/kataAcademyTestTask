package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    static int numberFirst, numberSecond;
    static char operation;
    static int result;

    public static void main (String[] args) {

        System.out.println("Input:");
        String input = scanner.nextLine();
        input = input.replaceAll(" ", "");

        operation = operatorSearch(input);
        if(operation == '\0')
            throw new InputMismatchException("Invalid data format");       

        String[] numbers = input.split("[+-/*]");
        String numStringFirst = numbers[0];
        String numStringSecond = numbers[1];

        numberFirst = romanToNumber(numStringFirst);
        numberSecond = romanToNumber(numStringSecond);

        if (numberFirst < 0 && numberSecond < 0) {
            result = 0;
        } else {
            result = calculation(numberFirst, numberSecond, operation);
            System.out.println("Output:");
            String resultRoman = convertNumToRoman(result);
            System.out.println(numStringFirst + " " + operation + " " + numStringSecond + " = " + resultRoman);
        }

        numberFirst = Integer.parseInt(numStringFirst);
        numberSecond = Integer.parseInt(numStringSecond);
        result = calculation(numberFirst, numberSecond, operation);
        System.out.println("Output:");
        System.out.println(numberFirst + " " + operation + " " + numberSecond + " = " + result);
    }

    private static String convertNumToRoman (int number) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        return roman[number];
    }


    private static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid data format");
        }
        return -1;
    }

    public static int calculation(int numberFirst, int numberSecond, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = numberFirst + numberSecond;
                break;
            case '-':
                result = numberFirst - numberSecond;
                break;
            case '*':
                result = numberFirst * numberSecond;
                break;
            case '/':
                try {
                    result = numberFirst / numberSecond;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception: " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation sign");
        }
        return result;
    }

    public static char operatorSearch(String input){
        for (char ch : input.toCharArray()) {
            if(ch=='+' | ch=='-' | ch=='*' | ch=='/'){
                return ch;
            }
        }
        return '\0';
    }
}