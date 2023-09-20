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
        System.out.println(calc(input));

    }

    public static String calc(String input){
        input = input.replaceAll(" ", "");

        int charIndex = input.indexOf('.');
        if(charIndex != -1)
            throw new InputMismatchException("Numbers must be integer");
        charIndex = input.indexOf(',');
        if(charIndex != -1)
            throw new InputMismatchException("Numbers must be integer");

        operation = operatorSearch(input);
        if(operation == '\0')
            throw new InputMismatchException("Invalid data format");

        String[] numbers = input.split("[+-/*]");
        String numStringFirst = numbers[0];
        String numStringSecond = numbers[1];

        if(romanToNumber(numStringFirst) == -1 | romanToNumber(numStringSecond) == -1)
            return arabicCalculation(numStringFirst, numStringSecond);
        else
            return romanCalculation(numStringFirst, numStringSecond);
    }

    public static String romanCalculation(String numStringFirst, String numStringSecond){
        numberFirst = romanToNumber(numStringFirst);
        numberSecond = romanToNumber(numStringSecond);

        String resultRoman;

        if (numberFirst < 0 && numberSecond < 0) {
            result = 0;
        } else {
            result = calculation(numberFirst, numberSecond, operation);
        }

        resultRoman = convertNumToRoman(result);
        return "Output:\n" + numStringFirst + " " + operation + " " + numStringSecond + " = " + resultRoman;
    }

    public static String arabicCalculation(String numStringFirst, String numStringSecond){

        numberFirst = Integer.parseInt(numStringFirst);
        numberSecond = Integer.parseInt(numStringSecond);

        if(numberFirst < 0 | numberSecond < 0)
            throw new InputMismatchException("Numbers must to be more then 0");
        if(numberFirst > 10 | numberSecond > 10)
            throw new InputMismatchException("Numbers must to be less then 10");

        result = calculation(numberFirst, numberSecond, operation);

        return "Output:\n" + numberFirst + " " + operation + " " + numberSecond + " = " + result;
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