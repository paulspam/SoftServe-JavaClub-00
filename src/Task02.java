/**
 * Задание 16.15
 * <p>
 * Два положительных трехзначных числа а и b называются «родственными», если
 * сумма делителей числа а равна Ъ, а сумма делителей числа b равна а.
 * Например, это числа 220 и 284, так как сумма делителей 220 равна 284,
 * а сумма делителей 284 равна 220.
 * <p>
 * Напишите класс, который принимает с клавиатуры положительное трехзначное
 * число и ищет ближайшее к нему и большее него «родственное» число.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task02 {
    public static void main(String[] args) {
        int number = get3DigitsNumber();
        if (number == -1) {
            return;
        }
        int nearestRelative = getNearestRelative(number);
        if (nearestRelative == -1) {
            System.out.println("Ближайшее большее родственное число для " + number + " не найдено");
        } else {
            System.out.println("Ближайшее большее родственное число для " + number + ": " + nearestRelative);
        }
    }

    /**
     * Принимает с клавиатуры число и проверяет, является ли оно трехзначным положительным
     *
     * @return введенное число или -1 если введенное число не является положительным трехзначным
     */
    private static int get3DigitsNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number;
        try {
            System.out.print("Введите положительное трехзначное число: ");
            number = Integer.parseInt(reader.readLine());
            if ((number < 100) || (number > 999)) {
                System.out.println("Введенное число не положительное трехзначное!");
                return -1;
            }
        } catch (IOException e) {
            System.out.println("Метод завершен с IOException.");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Метод завершен с NumberFormatException.");
            return -1;
        }
        return number;
    }

    /**
     * Подсчитывает сумму делителей числе, переданного в переметре
     *
     * @param number число, сумму делителей которого необходимо посчитать
     * @return сумму делителей числа {@code number}
     */
    private static int sumOfDivisors(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * Ищет ближайшее большее родственное число
     *
     * @param number число, для которого ищем ближайшее большее родственное число
     * @return ближайшее большее родственное число
     */
    private static int getNearestRelative(int number) {
        int nearestRelative = number;
        do {
            nearestRelative++;
            if ((nearestRelative) == 1000) {
                nearestRelative = -1;
                break;
            }
        } while (!((number == sumOfDivisors(nearestRelative)) && (nearestRelative == sumOfDivisors(number))));
        return nearestRelative;
    }
}
