package gb.lesson1;

import java.io.IOException;

public class Introduction {

    public static void main(String[] args)
    {
        var example = new Introduction();

    }
    /**
     * 1. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат с плавающей точкой,
     * где a, b, c, d – целочисленные входные параметры этого метода;
     */
    public double expression(int a, int b, int c, int d){
        return a * (b + (double) c/d);
    }
    /**
     * 2. Написать метод, принимающий на вход два целых числа, и проверяющий что их сумма лежит в пределах
     * от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
     */
    public boolean check_interval(int a, int b){
        return ((a+b)>=10 && (a+b)<=20)?true:false;
    }
    /**
     * 3. Написать метод, которому в качестве параметра передается целое число,
     * метод должен проверить положительное ли число передали, или отрицательное.
     * Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль
     */
    public String check_positive_v1(int a){
        return a>=0?"число положительное":"число отрицательное";
    }
    public boolean check_positive_v2(int a){
        return a>=0?true:false;
    }
    /**
     * 4. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
     * метод должен вернуть приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.
     */
    public String welcoming(String name){
        return "Привет, " + name + "!";
    }
    /**
     * 5.Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным,
     * кроме каждого 100-го, при этом каждый 400-й – високосный.
     * Для проверки работы вывести результаты работы метода в консоль
     */
    public String check_leap_year_v1(int year){
        return ((year % 100 != 0 || year % 400 == 0) && year % 4 == 0)? year + " - високосный год":year + " - не високосный год";
    }
    public boolean check_leap_year_v2(int year){
        return (year % 100 == 0 )?(year % 400 == 0):(year % 4 == 0);
    }
}