public class Employee {

    /**
     * 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
     */
    private String FIO;
    private String position;
    private String email;
    private int numberPhone;
    private int salary;
    private int age;
    static private int countEmployee = 0;

    public static void main(String[] args) {
        /**
         * 4. Создать массив из 5 сотрудников.
         */
        Employee[] employee = new Employee[5];
        employee[0] = new Employee("Иванов Иван Иванович", "менеджер", "IvanovII@mail.ru", 892312312, 60000, 43);
        employee[1] = new Employee("Петров Иван Сергеевич", "разработчик", "PetrovIS@mail.ru", 896532049, 90000, 26);
        employee[2] = new Employee("Сергеев Сергей Петрович", "HR", "SergeevSP@mail.ru", 893726712, 50000, 28);
        employee[3] = new Employee("Андреев Александр Иванович", "аналитик", "AndreevAI@mail.ru", 895646730, 80000, 40);
        employee[4] = new Employee("Королев Лев Николаевич", "тестировщик", "KorolevLN@mail.ru", 897635690, 90000, 31);
        /**
         * 5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
         */
        for (int i = 0; i < employee.length; i++) {
            if (employee[i].age >= 40) employee[i].printEmployee();
        }
        System.out.println("Всего сотрудников создано: " + countEmployee);

        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton==singleton1); // проверка на то, что создался один и тот же объект. Адреса должны совпадать


    }

    /**
     * 2. Конструктор класса должен заполнять эти поля при создании объекта.
     */
    public Employee(String FIO, String position, String email, int numberPhone, int salary, int age) {
        this.FIO = FIO;
        this.position = position;
        this.email = email;
        this.numberPhone = numberPhone;
        this.salary = salary;
        this.age = age;
        /**
         * 6. Реализуйте в классе сотрудников подсчет каждого созданного сотрудника
         */
        countEmployee++;
    }

    /**
     * 3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
     */
    public void printEmployee() {
        System.out.println("ФИО сотрудника: " + this.FIO
                + ". Должность: " + this.position
                + ". email: " + this.email
                + ". Телефон " + this.numberPhone
                + ". Зарплата: " + this.salary
                + ". Возраст " + this.age);
    }
}
