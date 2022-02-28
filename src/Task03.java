/**
 * Задание 22.28
 * <p>
 * Внутренний элемент двумерного массива называется «локальным максимумом»,
 * если его значения больше значений всех 8 соседних ячеек.
 * Глобальный максимум массива это самое большое значение, которое в нем находится.
 * <p>
 * 1. Напишите метод, который получает в качестве параметра двумерный массив
 * целых чисел и два дополнительных целых параметра С и К, определяющих
 * местоположение внутреннего элемента массива. Метод должен проверить,
 * является ли данный элемент массива локальным максимумом.
 * <p>
 * 2. Напишите метод, который получает в качестве параметра двумерный массив
 * целых чисел и выводит на экран координаты всех локальных максимумов.
 * <p>
 * 3.	Напишите метод, который проверяет, является ли глобальный максимум
 * одновременно и локальным.
 */

/**
 * P.S. Что касается 3-го пункта задачи, то, если я правильно понял, то глобальный
 * максимум - это максимальное значение в массиве и это значение всегда будет
 * локальным максимумом. Но метод для проверки этого я написал ;) *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Task03 {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int rows;     // Количество строк в массиве
        int columns;  // Количество колонок в массиве
        int maxValue; // Максимальное значение, которое может быть в массиве

        try {
            System.out.print("Введите количество строк в массиве: ");
            rows = Integer.parseInt(input.readLine());
            System.out.print("Введите количество колонок в массиве: ");
            columns = Integer.parseInt(input.readLine());
            if (rows < 1 || columns < 1) {
                System.out.println("Параметры массива недопустимы!");
                return;
            }
            System.out.print("Введите максимальное значение элемента массива: ");
            maxValue = Integer.parseInt(input.readLine());
            if (maxValue < 2) {
                System.out.println("Введенное значение недопустимо!");
                return;
            }
        } catch (IOException e) {
            System.out.println("Программа завершена с IOException.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Программа завершена с NumberFormatException.");
            return;
        }

        int[][] array = new int[rows][columns];
        fillArrayRandomValues(array, maxValue);

        System.out.println("\nИсходный массив:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nПроверяем является ли элемент массива локальным максимумом");
        int checkLocalMaxRow = -1;
        int checkLocalMaxColumn = -1;
        try {
            System.out.println("Введите индексы проверяемой ячейки (начальный индекс - 0)");
            System.out.print("строка: ");
            checkLocalMaxRow = Integer.parseInt(input.readLine());
            System.out.print("колонка: ");
            checkLocalMaxColumn = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            System.out.println("Программа завершена с IOException.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Программа завершена с NumberFormatException.");
            return;
        }
        if ((checkLocalMaxRow < 0) || (checkLocalMaxRow > array.length - 1)
                || (checkLocalMaxColumn < 0) || (checkLocalMaxColumn > array[0].length - 1)) {
            System.out.println("Индексы проверяемой ячейки за границей матрицы");
            return ;
        }
        if (isLocalMax(array, checkLocalMaxRow, checkLocalMaxColumn)) {
            System.out.println("Элемент с индексами [" + checkLocalMaxRow + "][" + checkLocalMaxColumn + "] является локальным максимумом");
        } else {
            System.out.println("Элемент с индексами [" + checkLocalMaxRow + "][" + checkLocalMaxColumn + "] не является локальным максимумом");
        }

        System.out.println("\nВыводим индексы всех локальных максимумов:");
        printLocalMaxIndexes(array);

        System.out.println("\nПроверяем, является ли глобальный максимум одновременно и локальным ;)");
        if (isGlobalAndLocalMax(array)) {
            System.out.println("Глобальный максимум является и локальным максимумом");
        } else {
            System.out.println("Глобальный максимум не является локальным максимумом");
        }
    }

    /**
     * Заполняет массив случайными значениями
     *
     * @param array    массив, который необходимо заполнить
     * @param maxValue максимальное значение, которым будет заполнен массив
     */
    private static void fillArrayRandomValues(int[][] array, int maxValue) {
        Random random = new java.util.Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = random.nextInt(maxValue) + 1;
            }
        }
    }

    /**
     * Проверяет, является ли элемент локальным максимумом
     *
     * @param array  массив, в котором находится проверяемый элемент
     * @param row    индекс строки, в которой находится проверяемый элемент
     * @param column индекс столбца, в которой находится проверяемый элемент
     * @return {@code true} если элемент является локальным максимумом
     */
    private static boolean isLocalMax(int[][] array, int row, int column) {
        int[] localArray = new int[8]; // массив соседних элементов
        int m = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if ((i == row) && (j == column)) {
                    continue;
                }
                if ((i < 0) || (j < 0) || (i > array.length - 1) || (j > array[0].length - 1)) {
                    localArray[m] = Integer.MIN_VALUE;
                } else {
                    localArray[m] = array[i][j];
                }
                m++;
            }
        }
        int maxNeighbour = Arrays.stream(localArray).max().getAsInt();
        if (array[row][column] > maxNeighbour) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Выводит на экран индексы всех локальных максимумов
     *
     * @param array массив, в котором необходимо найти локальные максимумы
     */
    private static void printLocalMaxIndexes(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (isLocalMax(array, i, j)) {
                    System.out.println("Элемент с индексами [" + i + "][" + j + "] является локальным максимумом");
                }
            }
        }
    }

    /**
     * Проверяет, является ли глобальный максимум одновременно и локальным
     *
     * @param array  массив для проверки
     * @return {@code true} если глобальный максимум одновременно и локальным
     */
    private static boolean isGlobalAndLocalMax(int[][] array) {
        int globalMax = Integer.MIN_VALUE;
        int globalMaxRow = -1;
        int globalMaxColumn = -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] > globalMax) {
                    globalMax = array[i][j];
                    globalMaxRow = i;
                    globalMaxColumn = j;
                }
            }
        }
        if (isLocalMax(array, globalMaxRow, globalMaxColumn)) {
            return true;
        } else {
            return false;
        }
    }
}