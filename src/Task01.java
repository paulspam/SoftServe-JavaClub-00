/**
 * Задание 9.16
 * <p>
 * Определим следующие периоды в течение суток:
 * утро — с 6 (включая) до 10 (включая) часов,
 * день — с 10 (не включая) до 18 (включая),
 * вечер — с 18 (не включая) до 22 (включая),
 * ночь — с 22 (не включая) до 6 часов следующего дня (не включая).
 * <p>
 * Напишите класс, который принимает с клавиатуры целое число, которое должно
 * указывать на определенный час в сутках, и выводит на экран сообщение,
 * к какому периоду в сутках этот час относится. В случае если введенное
 * значение не позволяет выполнить указанное действие, следует вывести
 * на экран соответствующее текстовое сообщение.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01 {
    public static void main(String[] args) {
        int hour = inputHourOfDay();
        getPeriodOfDay(hour);
    }

    /**
     * Считывает с клавиатуры час суток и проверяет на корректность
     *
     * @return введенный час суток или -1 если введенное значени не корректно
     */
    private static int inputHourOfDay() {
        int hour = -1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите час суток: ");
            hour = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Это не целочисленное значение!");
            return -1;
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
            return -1;
        }
        return hour;
    }

    /**
     * Выводит на экран к какому периоду суток относится полученный в параметре час суток
     *
     * @param hour час суток
     */
    private static void getPeriodOfDay(int hour) {
        if ((hour >= 6) && (hour <= 10)) {
            System.out.println(PeriodOfDay.MORNING);
        } else if ((hour > 10) && (hour <= 18)) {
            System.out.println(PeriodOfDay.DAY);
        } else if ((hour > 18) && (hour <= 22)) {
            System.out.println(PeriodOfDay.EVENING);
        } else if (((hour > 22) && (hour <= 24)) || (hour >= 0) && (hour < 6)) {
            System.out.println(PeriodOfDay.NIGHT);
        } else {
            System.out.println("Введенное значение не является часом суток!");
        }
    }

    public enum PeriodOfDay {
        NIGHT("Ночь"),
        MORNING("Утро"),
        DAY("День"),
        EVENING("Вечер");

        private String title;

        PeriodOfDay(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
