package gb.lesson6.animals;

public abstract class Animal {

    protected String name;
    static public int countAnimal = 0;

    public Animal() {
        countAnimal++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);
}
