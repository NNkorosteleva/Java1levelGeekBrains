package gb.lesson7;

import java.io.IOException;

public class Main {
    /**
     * Создаем массив котов с именем и аппетитом (сытость по умолчанию false).
     * Создаем тарелку с кол-вом еды
     * Как только еда заканчивается или коту мало оставшейся еды вызывается метод возможности добавления еды.
     * Если пользователь решается добавить еду, то вводит кол-во еды и процесс идет дальше:
     * проверка на достаточность еды, вычитание еды с тарелки, вывод сытости кота и информации о еде в тарелке
     * Если пользователь отказывается добавлять еды, то вывод сытости кота, информации о еде в тарелке и переходим к следующему коту
     */
    public static void main(String[] args) throws IOException {
        Cat[] cat = new Cat[3];
        cat[0] = new Cat("Barsik", 5);
        cat[1] = new Cat("Murzik", 3);
        cat[2] = new Cat("Pushok", 8);
        Plate plate = new Plate(6);

        for (int i = 0; i < cat.length; i++) {
            plate.info();
            cat[i].info();
            cat[i].eat(plate);
            plate.info();
            cat[i].info();
        }
    }
}
