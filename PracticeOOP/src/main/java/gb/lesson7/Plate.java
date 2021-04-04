package gb.lesson7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Конструктор Plate задает кол-во еды в тарелке
 *
 * Метод eatFood(int n) возвращает true, если кот поел n еды, false если еды оказалось мало для кота
 *
 * Метод decreaseFood(int n) должен вычитать из тарелки запрашиваемое кол-во n еды. Если удалось возвращает true,
 * если кол-во запрашиваемой еды больше чем в тарелке, то возвращает false и еда в тарелке не убавляется
 *
 * Метод addFood() должен добавлять еду в тарелку на кол-во, которое ввел пользователь
 * Возвращает true, если пользователь добавил еды, false если отказался добавлять еду
 *
 * Метод info() показывает кол-во еды в тарелке на данный момент
 */

public class Plate {

    private int food;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Plate(int food) {
        this.food = food;
    }

    public boolean eatFood(int n) throws IOException {
        while (!decreaseFood(n)) {
            System.out.println("not enough food in the plate");
            if (!addFood()) return false;
        }
        return true;
    }

    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        }
        return false;
    }

    public boolean addFood() throws IOException {
        String s;
        do {
            System.out.println("would you like to add food? 0 - NOT, 1 - YES");
            s = reader.readLine();
        } while ((!s.equals("1")) && (!s.equals("0")));
        if (s.equals("1")) {
            System.out.println("enter the amount of food");
            food += Integer.parseInt(reader.readLine());
            return true;
        }
        return false;
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}
