package gb.lesson7;

import java.io.IOException;

/**
 * Конструктор Cat задает имя, аппетит и сытость кота
 *
 * Метод eat(Plate p) возвращает сытость кота - удалось ему поесть (true) или нет (false)
 *
 * Метод info() показывает кол-во еды в тарелке на данный момент
 */

public class Cat {

    private String name;
    private int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public void eat(Plate p) throws IOException {
        fullness = p.eatFood(appetite);
    }

    public void info() {
        System.out.println(name + " is full: " + fullness);
    }

}
