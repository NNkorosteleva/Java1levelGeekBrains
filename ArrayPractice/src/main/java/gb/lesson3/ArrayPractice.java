package gb.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArrayPractice {
    public static void main(String[] args) throws IOException {
        ArrayPractice practice = new ArrayPractice();
        practice.game_number(3);
    }

    /**
     * 1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать
     * это число. При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное,
     * или меньше. После победы или проигрыша выводится запрос –
     * «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    private void game_number(int attempt) throws IOException {
        ArrayPractice practice = new ArrayPractice();
        int number_AI = practice.intRandom(); // переменная значения программы
        int number_user, check; // переменные значения пользователя, проверка на совпадение значения пользователя и программы
        int attempt_start = attempt; // переменная начального значения кол-ва попыток
        while (attempt > 0) {
            number_user = practice.input(); // пользователь вводит число
            check = practice.checkN(number_AI, number_user); // проверка совпадения; 0 - не совпало, 1 - совпало
            if (check == 0) {
                attempt--;
                if (attempt == 0) {
                    System.out.println("К сожалению, Вы не угадали число. Компьютер загадал число " + number_AI);
                    System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
                    attempt = (practice.input_end_game() == 1) ? attempt_start : 0;
                    number_AI = practice.intRandom();
                } else {
                    System.out.println("У вас осталось попыток: " + attempt);
                }
            } else {
                break;
            }
        }
    }

    /**
     * Метод для ввода числа пользователем с проверкой на корректность ввода значения:
     * 1. является ли числом
     * 2. значение находится в инетрвале [0,9]
     * Если значение введено не корректно, то просим пользователя снова ввести число
     */
    private int input() throws IOException, IllegalArgumentException {
        int n_input = 0;
        int check_repeat = 0;
        do {
            check_repeat = 0;
            System.out.print("Введите число от 0 до 9: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                n_input = Integer.parseInt(reader.readLine());
                if ((n_input < 0) || (n_input > 9))
                    throw new IllegalArgumentException();
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число. Попроуйте еще раз");
                check_repeat = 1;
            } catch (IllegalArgumentException e) {
                System.out.println("Нужно ввести число от 0 до 9. Попроуйте еще раз");
                check_repeat = 1;
            }
        } while (check_repeat == 1);

        return n_input;
    }

    /**
     * Метод для ввода числа пользователем 1 или 0 с проверкой на корректность ввода значения:
     * 1. является ли числом
     * 2. принимает значение 1 или 0
     * Если значение введено не корректно, то игра завершается
     */
    private int input_end_game() throws IOException, IllegalArgumentException {
        int check = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            check = Integer.parseInt(reader.readLine());
            if ((check != 1) && (check != 0))
                throw new IllegalArgumentException();
        } catch (NumberFormatException e) {
            System.out.println("Нужно было ввести ЧИСЛО. Game Over");
            check = 0;
        } catch (IllegalArgumentException e) {
            System.out.println("Нужно было ввести число 1 или 0. Game Over");
            check = 0;
        }
        return check;
    }

    /**
     * Метод для создания целочисленного рандомного числа, значение которого находится в интервале [0,9]
     */
    private int intRandom() {
        return (int) (Math.random() * 9);
    }

    /**
     * Метод для проверки указанного пользователем число
     */
    private int checkN(int n, int n_input) {
        int m = 0;
        if (n_input > n) {
            System.out.println("Введеное число больше, чем загаданное");
            m = 0;
        } else if (n_input < n) {
            System.out.println("Введеное число меньше, чем загаданное");
            m = 0;
        } else {
            System.out.println("Поздравляем, Вы угадали число!");
            m = 1;
        }
        return m;
    }
}