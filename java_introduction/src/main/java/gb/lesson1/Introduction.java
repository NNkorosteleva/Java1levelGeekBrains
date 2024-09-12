package gb.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Introduction {

    public static void main(String[] args) throws IOException {
        var example = new Introduction();
        /**
         * тестирование 1 задания
         */
        System.out.print("a = ");
        int a = setVariableInt();
        System.out.print("b = ");
        int b = setVariableInt();
        System.out.print("c = ");
        int c = setVariableInt();
        System.out.print("d = ");
        int d = setVariableInt();
        System.out.println("a * (b + (c / d)) = " + example.expression(a,b,c,d));
        /**
         * тестирование 2 задания
         */
        System.out.print("a = ");
        a = setVariableInt();
        System.out.print("b = ");
        b = setVariableInt();
        System.out.println("Сумма двух чисел лежит в интервале [10,20]? " + example.check_interval(a,b));
        /**
         * тестирование 3 задания
         */
        System.out.print("a = ");
        a = setVariableInt();
        System.out.println("Число положительное? " + example.check_positive_v1(a) + " " + example.check_positive_v2(a));
        /**
         * тестирование 4 задания
         */
        System.out.print("name = ");
        String name = setVariableString();
        System.out.println(example.welcoming(name));
        /**
         * тестирование 5 задания
         */
        System.out.print("год = ");
        a = setVariableInt();
        System.out.println(example.check_leap_year_v1(a)+ " " + example.check_leap_year_v2(a));
    }
    /**
     * 1. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат с плавающей точкой,
     * где a, b, c, d – целочисленные входные параметры этого метода;
     */
    private double expression(int a, int b, int c, int d){
        return a * (b + (double) c/d);
    }
    /**
     * 2. Написать метод, принимающий на вход два целых числа, и проверяющий что их сумма лежит в пределах
     * от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
     */
    private boolean check_interval(int a, int b){
        return ((a+b)>=10 && (a+b)<=20)?true:false;
    }
    /**
     * 3. Написать метод, которому в качестве параметра передается целое число,
     * метод должен проверить положительное ли число передали, или отрицательное.
     * Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль
     *
     * check_positive_v1 - выводит тексовое сообщение
     * check_positive_v2 - вывод true, если число положительное, false - отрицательное
     */
    private String check_positive_v1(int a){
        return a>=0?"число положительное":"число отрицательное";
    }
    private boolean check_positive_v2(int a){
        return a>=0?true:false;
    }
    /**
     * 4. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
     * метод должен вернуть приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.
     */
    private String welcoming(String name){
        return "Привет, " + name + "!";
    }
    /**
     * 5.Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным,
     * кроме каждого 100-го, при этом каждый 400-й – високосный.
     * Для проверки работы вывести результаты работы метода в консоль
     *
     * check_leap_year_v1 - выводит тексовое сообщение
     * check_leap_year_v2 - вывод true, если год високосный, false - не високосный год
     */
    private String check_leap_year_v1(int year){
        return ((year % 100 != 0 || year % 400 == 0) && year % 4 == 0)? year + " - високосный год":year + " - не високосный год";
    }
    private boolean check_leap_year_v2(int year){
        return (year % 100 == 0 )?(year % 400 == 0):(year % 4 == 0);
    }
    /**
     * статичный метод setVariableInt для ввода с клавиатуры целого числа
     * статичный метод setVariableString для ввода с клавиатуры символьной строки
     */
    private static int setVariableInt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }
    private static String setVariableString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
