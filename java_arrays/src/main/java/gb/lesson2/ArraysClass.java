package gb.lesson2;

public class ArraysClass {
    public static void main(String[] args) {

        ArraysClass arrays = new ArraysClass();
        /**
         * тестирование 1 задания
         */
        int[] arr = arrays.arrayRandomBinary(8);
        System.out.print("Старый массив:");
        arrays.arrayPrint(arr);

        int[] arr_inv = arrays.arrayReplacement(arr);
        System.out.print("\nИнверсия массива:");
        arrays.arrayPrint(arr_inv);
        /**
         * тестирование 2 задания
         */
        System.out.print("\nПрогрессия массива:");
        arrays.arrayPrint(arrays.arrayFillIn(10));
        /**
         * тестирование 3 задания
         */
        int[] arr_random = arrays.arrayRandom(10);
        System.out.print("\nМассив случайных целых чисел:");
        arrays.arrayPrint(arr_random);

        System.out.print("\nНовый массив:");
        arrays.arrayPrint(arrays.arrayReplace(arr_random));
        /**
         * тестирование 4 задания
         */
        System.out.println("\nМассив с главной диагональю:");
        arrays.arrayPrintDouble(arrays.arrayDiagonal(5));

        /**
         * тестирование 5 задания
         */
        int[] arr_random1 = arrays.arrayRandom(10);
        System.out.print("\nМассив случайных целых чисел:");
        arrays.arrayPrint(arr_random1);
        System.out.print("\nМаксимум: " + arrays.arrayMax(arr_random1));
        System.out.print("\nМинимум: " + arrays.arrayMin(arr_random1));
        /**
         * тестирование 6 задания
         */
        int[] arr_random2 = arrays.arrayRandom(10);
        System.out.print("\nМассив случайных целых чисел:");
        arrays.arrayPrint(arr_random2);
        System.out.print("\nМожно ли провести границу внутри массива? " + arrays.arrayCheckBalance(arr_random2));
    }

    /**
     * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    private int[] arrayReplacement(int[] arr) {
        int[] arr_inv = new int[arr.length];
        for (int i = 0; i < arr_inv.length; i++) {
            arr_inv[i] = (arr[i] == 0) ? 1 : 0;
        }
        return arr_inv;
    }

    /**
     * Метод для создания бинарного рандомного массива
     */
    private int[] arrayRandomBinary(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            double r = Math.random();
            arr[i] = (r >= 0.5) ? 1 : 0;
        }
        return arr;
    }

    /**
     * 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    private int[] arrayFillIn(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        return arr;
    }

    /**
     * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    private int[] arrayReplace(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] < 6) ? arr[i] * 2 : arr[i];
        }
        return arr;
    }

    /**
     * Метод для создания целочисленного рандомного массива, значения которого находятся в интервале [0,10]
     */
    private int[] arrayRandom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    /**
     * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
     */
    private int[][] arrayDiagonal(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i][i] = 1;
            arr[i][n - i - 1] = 1;
        }
        return arr;
    }

    /**
     * 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    private int arrayMax(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            max = (i > max) ? i : max;
        }
        return max;
    }

    private int arrayMin(int[] arr) {
        int min = arr[0];
        for (int i : arr) {
            min = (i < min) ? i : min;
        }
        return min;
    }

    /**
     * 6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
     * если в массиве есть место, в котором сумма левой и правой части массива равны.
     * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
     * граница показана символами ||, эти символы в массив не входят.
     */
    private boolean arrayCheckBalance(int[] arr) {
        int s = 0;
        int f = arr.length - 1;
        int sum1 = 0, sum2 = 0;
        while (s <= f) {
            if (sum1 < sum2) {
                sum1 += arr[s];
                s++;
            } else {
                sum2 += arr[f];
                f--;
            }
        }
        return sum1 == sum2;
    }

    /**
     * Метод для печати одномерного массива
     */
    private void arrayPrint(int[] arr) {
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }

    /**
     * Метод для печати двумерного массива
     */
    private void arrayPrintDouble(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println();
            for (int anInt : ints) {
                System.out.print(" " + anInt);
            }
        }
    }

}
