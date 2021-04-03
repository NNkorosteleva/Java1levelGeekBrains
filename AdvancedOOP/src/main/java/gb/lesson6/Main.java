package gb.lesson6;

import gb.lesson6.animals.Animal;

public class Main {
    public static void main(String[] args) {

        Cat catPushok = new Cat("Пушок");
        Cat catMurzik = new Cat("Мурзик");
        Dog dogSharik = new Dog("Шарик");
        Dog dogDruzhok = new Dog("Дружок");
        Dog dogBusya = new Dog("Буся");

        catPushok.run(150);
        catMurzik.run(210);
        catMurzik.swim(15);

        dogSharik.run(360);
        dogSharik.swim(15);
        dogDruzhok.run(550);
        dogDruzhok.swim(10);
        dogBusya.run(500);
        dogBusya.swim(8);

        System.out.println("Созданно котов: " + Cat.countCat);
        System.out.println("Созданно собак: " + Dog.countDog);
        System.out.println("Животных: " + Animal.countAnimal);
    }
}
