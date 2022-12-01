import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String a = s.nextLine().toUpperCase().trim();
        System.out.print(a);
        System.out.println(" = " + calc(a));
        s.close();
    }
    public static String calc(String input) {
        String[] elements;
        String output;
        elements = input.split(" ");
        if (elements.length != 3) {
            throw new NumberFormatException(" \nНеверный формат, принимаются только выражения с двумя числами и одним оператором формата a + b");
        }
        char oper = getOper(elements[1]);
        int num1 = romanToArabic(elements[0]);
        int num2 = romanToArabic(elements[2]);
        if (num1 > 0 && num2 > 0) {
            int res = (calculate(num1, num2, oper));
            if (res > 0) {
                output = arabicToRoman(res);
            } else throw new NumberFormatException(" \nВ римской системе счисления нет отрицательных чисел");
        } else {
            try {
                num1 = Integer.parseInt(elements[0]);
                num2 = Integer.parseInt(elements[2]);
            } catch(NumberFormatException e){
                throw new NumberFormatException(" \nОдновременно используются разные системы счисления либо выражение не является математической операцией");
                //Не знаю, как реализовать эти два исключения отдельно. Хотя тут их даже три: разные системы счисления,
                //символы не являются римскими числами и выражение правильное, но римские числа больше 10.
                //Можно попробовать реализовать перевод любого римского числа в арабское, тогда последние два
                //исключения можно будет обработать в теле метода, но у меня пока такой метод не получился))
            }
            if((num1 > 0 && num2 > 0) && (num1 <= 10 && num2 <= 10)) {
                output = Integer.toString(calculate(num1, num2, oper));
            } else throw new NumberFormatException(" \nКалькулятор работает только с целыми числами от 1 до 10");
        }
        return output;
    }
    static int[]    numbers = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    static String[] letters = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    static String arabicToRoman(int num) {
        String roman = "";
        int N = num;
        for(int i = 0; i < numbers.length; i++){
            while(N >= numbers[i]){
                roman += letters[i];
                N -= numbers[i];
            }
        }
        return roman;
    }
    static int romanToArabic(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }
    static int calculate(int num1, int num2, char oper) {
        return switch (oper) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalStateException(" \nНеверный знак математической операции: " + oper);
        };
    }
    static char getOper(String elem){
        return switch (elem) {
            case "+" -> '+';
            case "-" -> '-';
            case "*" -> '*';
            case "/" -> '/';
            default -> throw new IllegalStateException(" \nНеверный знак математической операции: " + elem);
        };
    }

}

