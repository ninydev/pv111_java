package org.itstep.classworks.feb;

import org.itstep.classworks.feb.lambda.MyOperation;
import org.itstep.classworks.feb.lambda.OperationsType;
import org.itstep.classworks.feb.lambda.UserFactory;
import org.itstep.entities.Role;
import org.itstep.entities.User;

import java.util.*;
import java.util.function.*;


public class Feb_12 implements Runnable
{
    @Override
    public void run() {
        calc();
    }

    private void systemInterfaces() {
        /**
         * Для операций сравнения (с возвращением true false) можно воспользоваться
         * функциональным (системным) интерфейсом Predicate
         */
        Predicate<Integer> positive;
        positive = (x) -> x > 0;
        positive.test(10);

        /**
         * Операции с 2 элементами - в результате которого получается тот же тип
         */
        BinaryOperator<Integer> plus;
        plus = (a,b) -> a + b;
        plus.apply(2,2);
        // UnaryOperator - 1  элемент

        /**
         * Принимает 1 аргумент 1 типа - возвращает другой тип
         */
        Function<User, String> toName;
        toName = user -> user.getName();
        // toName.apply()

        /**
         * Удобен для изменения операций например при переборе объектов в коллекции
         */
        Consumer<User> outName;
        outName = user -> System.out.println(user.getName());

        /**
         * Актуален для реализации фабричного подхода
         * Когда мы поручаем фабрике прцоес создания новых экземпляров объектов
         */
        Supplier<UUID> createID;
        createID = () -> UUID.randomUUID();
        createID.get();

        /**
         * Хорошей практикой является передача прав на создание экземпляров объектов
         * фрагменту кода (фабрике)
         * Тогда вы сможете контролировать процесс - и сразу менять состояния по умолчанию
         */
        UserFactory userFactory;
        userFactory = (name, email) -> {
            ArrayList<Role> roles = new ArrayList<>();
            return new User(name, email, "QeAsdZxc!23", roles);
        };


    }

    private MyOperation getOperation(OperationsType type) {
        MyOperation operation = null;
        switch (type) {
            case plus -> operation = (a, b) -> a + b;
            case minus -> operation = (a, b) -> a - b;
        }
        if(operation == null) {
            throw new IllegalArgumentException("Type not found");
        }
        return operation;
    }

    private Double doOperation(Double a, Double b, MyOperation operation){

        return operation.operation(a,b);
    }

    private void calc(){
        MyOperation plus;
        plus = (a, b) -> a + b;
        MyOperation minus;
        minus = (a, b) -> a - b;

        Double r = doOperation(1.0,1.0, plus);
        Double r1 = doOperation(1.0,1.0, minus);


    }

    private void byLambda() {
        // Создам переменную для операции
        MyOperation operation;
        // Загружу (реализую) операцию
        operation = (a, b) ->  a + b;

        System.out.println(operation.operation(2.9,2.2));

    }





    private void mapSet(){
        ArrayList<Role> roles = new ArrayList<>();
        User u0 = new User("Zinaida Nykytina", "zina@google.com", "QweAsdZxc!23", roles);
        User u1 = new User("Oleksandr Nykytin", "keeper@ninydev.com", "QweAsdZxc!23", roles);
        User u2 = new User("Svetlana Nykytina", "sveta@ninydev.com", "QweAsdZxc!23", roles);

        HashMap<String, User> users = new HashMap<>();

        users.put("Zina", u0);
        users.put("Sasha", u1);
        users.put("Sveta", u2);

        for(Map.Entry<String, User> item : users.entrySet()) {
            System.out.println(item.getKey() + " \t => \t" + item.getValue());
        }

    }

    private void sortedSet() {
        ArrayList<Role> roles = new ArrayList<>();
        User u0 = new User("Zinaida Nykytina", "zina@ninydev.com", "QweAsdZxc!23", roles);
        User u1 = new User("Oleksandr Nykytin", "keeper@ninydev.com", "QweAsdZxc!23", roles);
        User u2 = new User("Svetlana Nykytina", "sveta@ninydev.com", "QweAsdZxc!23", roles);

        TreeSet<User> users = new TreeSet<>();

        users.add(u0);
        users.add(u1);
        users.add(u2);

        for(User u: users) {
            System.out.println(u);
        }

    }

    private void byHash(){
        ArrayList<Role> roles = new ArrayList<>();
        User u1 = new User("Oleksandr Nykytin", "keeper@ninydev.com", "QweAsdZxc!23", roles);
        User u2 = new User("Oleksandr Nykytin", "keeper@ninydev.com", "QweAsdZxc!23", roles);

        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());

        HashSet<User> users = new HashSet<>();

        users.add(u1);
        users.add(u2);

        System.out.println(users.size());

    }
}
