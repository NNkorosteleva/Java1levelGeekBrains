package gb.lesson6;

import gb.lesson6.animals.Animal;

public class Dog extends Animal {

    static int countDog = 0;
    final private int DISTANCE_RUN = 500;
    final private int DISTANCE_SWIM = 10;

    public Dog(String name) {
        this.name = name;
        countDog++;
    }

    @Override
    public void run(int distance) {
        if (distance <= DISTANCE_RUN) System.out.println(name + " пробежал " + distance + " м.");
        else System.out.println(name + " не может бегать " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        if (distance <= DISTANCE_SWIM) System.out.println(name + " проплыл " + distance + " м.");
        else System.out.println(name + " не может плыть " + distance + " м.");
    }
}
