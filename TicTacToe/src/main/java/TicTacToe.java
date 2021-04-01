import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class TicTacToe {

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int SIZE = 5;
    public static char[][] mapGame;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Random random = new Random();

    public static void main(String[] args) throws IOException {

        TicTacToe toe = new TicTacToe();
        toe.initMap();
        while (true) {
            toe.humanTurn();
            toe.printMap();
            if (toe.checkWin(DOT_X)) {
                System.out.println("Победил человек!");
                break;
            }
            if (toe.mapFull()) {
                System.out.println("Ничья!");
                break;
            }
            toe.aiTurn();
            toe.printMap();
            if (toe.checkWin(DOT_O)) {
                System.out.println("Победил компьютер!");
                break;
            }
            if (toe.mapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }

    }

    /**
     * Метод initMap инициализации поля
     */
    private void initMap() {
        mapGame = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mapGame[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод printMap печати поля на экран
     */
    private void printMap() {
        for (int i = 0; i <= mapGame.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < mapGame.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < mapGame.length; j++) {
                System.out.print(mapGame[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Метод mapFull проверка поля на полноту
     */
    public boolean mapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mapGame[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    /**
     * Метод humanTurn ход человека
     */
    private void humanTurn() throws IOException {
        int x, y;
        do {
            System.out.println("Введите координаты в формате Y X от 1 до " + SIZE);
            y = Integer.parseInt(reader.readLine()) - 1;
            x = Integer.parseInt(reader.readLine()) - 1;
        } while (!checkValid(x, y));
        mapGame[y][x] = DOT_X;
    }

    /**
     * Метод checkValid проверка возможности установки ячейки
     */
    private static boolean checkValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        else if (mapGame[y][x] != DOT_EMPTY) return false;
        else return true;
    }

    /**
     * Метод aiTurn ход компьютера c возможностью блокировки хода игрока.
     * Как вариант искать самое большое число 'X' и в этой строке/столбце/диагонали ставить метку компьютера
     * Таким образом мешаем игроку завершить комбинацию
     */
    public void aiTurn() {
        TicTacToe toe = new TicTacToe();
        int[] turnUser = toe.checkTurnUser();
        int x = 0;
        int y = 0;
        System.out.println("Ход компьютера");
        switch (turnUser[0]) {
            case 0:
                do {
                    x = random.nextInt(SIZE);
                    y = turnUser[1];
                } while (!checkValid(x, y));
                break;
            case 1:
                do {
                    x = turnUser[1];
                    y = random.nextInt(SIZE);
                } while (!checkValid(x, y));
                break;
            case 2:
                do {
                    x = random.nextInt(SIZE);
                    y = x;
                } while (!checkValid(x, y));
                break;
            case 3:
                do {
                    x = random.nextInt(SIZE);
                    y = mapGame.length - x - 1;
                } while (!checkValid(x, y));
                break;
            default:
                do {
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                } while (!checkValid(x, y));
                break;
        }
        mapGame[y][x] = DOT_O;

    }

    /**
     * Метод checkTurnUser ищет самое большую последовательность 'X'. Возвращает массив:
     * Если в строке больше 'X', то возвращает метку 0 и номер строки
     * Если в столбце, то возвращает метку 1 и номер столбца
     * Если в диагонали, то возвращает метку 2 - если главная диагональ и 3 - если побочная диагональ
     */
    private int[] checkTurnUser() {
        int[] result = {0, 0};
        int countX, countEmpty;
        int countGlobal = 0;
        // Создаем стрим из строк mapGame - ищем в какой строке наибольшее кол-во 'X'
        Stream<Character> charStream1, charStream2;
        for (int i = 0; i < mapGame.length; i++) {
            charStream1 = new String(mapGame[i]).chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_X
            charStream2 = new String(mapGame[i]).chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_EMPTY
            countX = (int) charStream1.filter(x -> x.equals(DOT_X)).count();
            countEmpty = (int) charStream2.filter(x -> x.equals(DOT_EMPTY)).count();
            if (countGlobal < countX) {
                result[0] = (countEmpty > 0) ? 0 : -1;
                result[1] = i;
                countGlobal = countX;
            }
        }
        // Создаем стрим из столбцов mapGame - ищем в каком столбце наибольшее кол-во 'X'
        for (int i = 0; i < mapGame.length; i++) {
            Stream.Builder<Character> charStreamBuilderV1 = Stream.<Character>builder();
            Stream.Builder<Character> charStreamBuilderV2 = Stream.<Character>builder();
            for (int j = 0; j < mapGame.length; j++) {
                charStreamBuilderV1.add(mapGame[j][i]);
                charStreamBuilderV1.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_X
                charStreamBuilderV2.add(mapGame[j][i]);
                charStreamBuilderV2.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_EMPTY
            }
            countX = (int) charStreamBuilderV1.build().filter(x -> x.equals(DOT_X)).count();
            countEmpty = (int) charStreamBuilderV2.build().filter(x -> x.equals(DOT_EMPTY)).count();
            if (countGlobal < countX) {
                result[0] = (countEmpty > 0) ? 1 : -1;
                result[1] = i;
                countGlobal = countX;
            }
        }
        // Создаем стрим из главных диагоналей mapGame - ищем в какой диагонали наибольшее кол-во 'X'
        Stream.Builder<Character> charStreamBuilderD11 = Stream.<Character>builder();
        Stream.Builder<Character> charStreamBuilderD12 = Stream.<Character>builder();
        Stream.Builder<Character> charStreamBuilderD21 = Stream.<Character>builder();
        Stream.Builder<Character> charStreamBuilderD22 = Stream.<Character>builder();
        for (int i = 0; i < mapGame.length; i++) {
            charStreamBuilderD11.add(mapGame[i][i]);
            charStreamBuilderD11.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_X для главной диагонали
            charStreamBuilderD12.add(mapGame[i][mapGame.length - i - 1]);
            charStreamBuilderD12.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_EMPTY для главной диагонали
            charStreamBuilderD21.add(mapGame[i][i]);
            charStreamBuilderD21.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_X для побочной диагонали
            charStreamBuilderD22.add(mapGame[i][mapGame.length - i - 1]);
            charStreamBuilderD22.toString().chars().mapToObj(x -> (char) x); // Поток для подсчета кол-ва DOT_EMPTY для побочной диагонали
        }
        countX = (int) charStreamBuilderD11.build().filter(x -> x.equals(DOT_X)).count();
        countEmpty = (int) charStreamBuilderD12.build().filter(x -> x.equals(DOT_EMPTY)).count();
        if (countGlobal < countX) {
            result[0] = (countEmpty > 0) ? 2 : -1;
            countGlobal = countX;
        }
        countX = (int) charStreamBuilderD21.build().filter(x -> x.equals(DOT_X)).count();
        countEmpty = (int) charStreamBuilderD22.build().filter(x -> x.equals(DOT_EMPTY)).count();
        if (countGlobal < countX) {
            result[0] = (countEmpty > 0) ? 3 : -1;
            countGlobal = countX;
        }
        return result;
    }

    /**
     * Метод checkWin проверка победы. Методу передается какую метку проверяем и если нашли комбинацию,
     * то возвращаем true. Данный метод работает для любого размера поля - нужно лишь изменить константу SIZE и
     * для любого кол-ва играков - нужно передать методу нужную метку
     */
    private boolean checkWin(char label) {
        int countH, countV;
        int countD1 = 0;
        int countD2 = 0;
        for (int i = 0; i < mapGame.length; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j < mapGame.length; j++) {
                if (mapGame[i][j] == label) countH++;
                if (mapGame[j][i] == label) countV++;
            }
            if (countH == SIZE) return true;
            if (countV == SIZE) return true;

            if (mapGame[i][i] == label) countD1++;
            if (mapGame[i][mapGame.length - i - 1] == label) countD2++;
        }
        if (countD1 == SIZE) return true;
        if (countD2 == SIZE) return true;

        return false;
    }
}
