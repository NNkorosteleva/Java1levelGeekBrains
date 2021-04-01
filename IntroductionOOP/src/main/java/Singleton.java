/**
 * 6. Придумайте на любом другом вами созданном классе, как можно сделать так,
 * чтобы мы не могли напрямую создать его экземпляр, и чтобы он создавался один на все приложение
 */

public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
