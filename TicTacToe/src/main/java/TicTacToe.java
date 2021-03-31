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
    public static final int SIZE = 10;
    public static char[][] mapGame;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Random random = new Random();

    public static void main(String[] args) throws IOException {

        extracted();

    }

    private static void extracted() throws IOException {
        TicTacToe toe = new TicTacToe();
        toe.initMap();
        toe.printMap();

        for (int i = 0; i < 4; i++) {
            toe.humanTurn();
            toe.printMap();
            toe.aiTurn();
            toe.printMap();
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
        int count = 0;
        int countGlobal = 0;
        // Создаем стрим из строк mapGame - ищем в какой строке наибольшее кол-во 'X'
        Stream<Character> charStream;
        for (int i = 0; i < mapGame.length; i++) {
            charStream = new String(mapGame[i]).chars().mapToObj(x -> (char) x);
            count = (int) charStream.filter(x -> x.equals('X')).count();
            if (countGlobal < count) {
                result[0] = 0;
                result[1] = i;
                countGlobal = count;
            }
        }
        // Создаем стрим из столбцов mapGame - ищем в каком столбце наибольшее кол-во 'X'
        for (int i = 0; i < mapGame.length; i++) {
            Stream.Builder<Character> charStreamBuilderV = Stream.<Character>builder();
            for (int j = 0; j < mapGame.length; j++) {
                charStreamBuilderV.add(mapGame[j][i]);
                charStreamBuilderV.toString().chars().mapToObj(x -> (char) x);
            }
            count = (int) charStreamBuilderV.build().filter(x -> x.equals('X')).count();
            if (countGlobal < count) {
                result[0] = 1;
                result[1] = i;
                countGlobal = count;
            }
        }
        // Создаем стрим из главных диагоналей mapGame - ищем в какой диагонали наибольшее кол-во 'X'
        Stream.Builder<Character> charStreamBuilderD1 = Stream.<Character>builder();
        Stream.Builder<Character> charStreamBuilderD2 = Stream.<Character>builder();
        for (int i = 0; i < mapGame.length; i++) {
            charStreamBuilderD1.add(mapGame[i][i]);
            charStreamBuilderD1.toString().chars().mapToObj(x -> (char) x);
            charStreamBuilderD2.add(mapGame[i][mapGame.length - i - 1]);
            charStreamBuilderD2.toString().chars().mapToObj(x -> (char) x);
        }
        count = (int) charStreamBuilderD1.build().filter(x -> x.equals('X')).count();
        if (countGlobal < count) {
            result[0] = 2;
            countGlobal = count;
        }
        count = (int) charStreamBuilderD2.build().filter(x -> x.equals('X')).count();
        if (countGlobal < count) {
            result[0] = 3;
            countGlobal = count;
        }

        return result;
    }

}
