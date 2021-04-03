package gb.lesson6;

import gb.lesson6.animals.Animal;

public class Cat extends Animal {

    static int countCat = 0;
    final private int DISTANCE_RUN = 200;

    public Cat(String name) {
        this.name = name;
        countCat++;
    }

    @Override
    public void run(int distance) {
        if (distance <= DISTANCE_RUN) System.out.println(name + " пробежал " + distance + " м.");
        else System.out.println(name + " не может бегать " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }
}
