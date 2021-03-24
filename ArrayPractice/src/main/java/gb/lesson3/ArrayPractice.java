package gb.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ArrayPractice {
    public static void main(String[] args) throws IOException {
        ArrayPractice practice = new ArrayPractice();
        practice.gameNumber(3);
        practice.gameWord();
    }

    /**
     * 1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать
     * это число. При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное,
     * или меньше. После победы или проигрыша выводится запрос –
     * «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    private void gameNumber(int attempt) throws IOException {
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
                    attempt = (practice.inputEndGame() == 1) ? attempt_start : 0;
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
                if ((n_input < 0) || (n_input > 9)) throw new IllegalArgumentException();
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести ЧИСЛО. Попроуйте еще раз");
                check_repeat = 1;
            } catch (IllegalArgumentException e) {
                System.out.println("Нужно ввести число от 0 до 9. Попроуйте еще раз");
                check_repeat = 1;
            } catch (IOException e) {
                System.out.println("Произошла непредвиденная ошибка. Попроуйте еще раз");
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
    private int inputEndGame() throws IOException, IllegalArgumentException {
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
        return (int) (Math.random() * 10); // умножаю на 10, потому что самый максимум что вернет Math.random() это 0.99999.. и при умножении на 10 с (int) даст 9
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

    /**
     * 2. * Создать массив из слов
     * String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
     * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
     * apple – загаданное
     * apricot - ответ игрока
     * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
     * Для сравнения двух слов посимвольно можно пользоваться:
     * String str = "apple";
     * char a = str.charAt(0); - метод вернет char, который стоит в слове str на первой позиции
     * Играем до тех пор, пока игрок не отгадает слово.
     * Используем только маленькие буквы.
     */
    private void gameWord() throws IOException {
        ArrayPractice practice = new ArrayPractice();
        int check = 0; // счетчик завершения игры
        String stringAI = practice.wordRandom(); // компьютер закадывает слово
        do{
            String stringUser = practice.wordUser(); // пользователь загадывает слово
            String checkWord = practice.checkWord(stringUser,stringAI); // проверка на совпадение слов
            int checkAnswer = practice.checkAnswer(checkWord); // счетчик совпадения
            if (checkAnswer==2){
                check=1;
                System.out.println("Поздравляем! Вы угадали слово " + stringAI);
            }
            else if (checkAnswer==1) System.out.println("Вы частично угадали слово " + checkWord + ". Попробуйте еще раз");
            else System.out.println("Вы не угадали слово. Попробуйте еще раз");
        }while (check==0);
    }

    /**
     * Метод для выбора рандомного слова из массива words
     */
    private String wordRandom() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        return words[(int) (Math.random() * (words.length))];
    }

    /**
     * Метод для ввода пользователем слова и проверкой на корректность ввода:
     * 1. пустая строка
     * 2. содержит цифры
     * Если значение введено не корректно, то просим пользователя снова ввести слово
     */
    private String wordUser() throws IOException, IllegalArgumentException {
        String sUser = null;
        int check_repeat = 0;
        do {
            check_repeat = 0;
            System.out.print("Введите слово: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                sUser = reader.readLine();
                if ((Pattern.matches(".+[0-9].+", sUser)) || (sUser.equals(""))) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректно введенное слово. Попроуйте еще раз");
                check_repeat = 1;
            } catch (IOException e) {
                System.out.println("Произошла непредвиденная ошибка. Попроуйте еще раз");
                check_repeat = 1;
            }
        } while (check_repeat == 1);
        return sUser.toLowerCase();
    }
    /**
     * Метод для проверки совпадения слов
     */
    private String checkWord(String sUser, String sAI) {
        String sReturn = null;
        if (sUser.equals(sAI)) sReturn = sAI;
        else {
            sReturn = "#".repeat(15);
            char[] chArray = sReturn.toCharArray();
            int n = (sUser.length() > sAI.length()) ? sAI.length() : sUser.length();
            for (int i = 0; i < n; i++) {
                if (sUser.charAt(i) == sAI.charAt(i)) chArray[i] = sAI.charAt(i);
            }
            sReturn = String.valueOf(chArray);
        }
        return sReturn;
    }
    /**
     * Метод, который выдает код после результата проверки совпадения слов
     * 0 - полностью  не совпали слова
     * 1 - частично совпали
     * 2 - полностью совпали
     */
    private int checkAnswer(String s) {
        return Pattern.matches(".+#.+", s) ? Pattern.matches(".*[a-z].*", s) ? 1 : 0 : 2;
    }
}