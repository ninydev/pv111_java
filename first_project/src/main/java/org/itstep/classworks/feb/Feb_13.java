package org.itstep.classworks.feb;

import org.itstep.entities.Person;
import org.itstep.entities.types.AvailableColor;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Feb_13 implements Runnable
{

    @Override
    public void run() {
        asFind();
    }

    private void finishOperation(Optional<Person> o) {
        Person p =  o.get();
    }

    private void asFind() {
        System.out.println(people.parallelStream().findAny().get());
        System.out.println(people.parallelStream().findFirst().get());

        // Обертка операции
        Optional<Person> whenINeedFind = Optional.of(people.parallelStream()
                // Попробовать выполнить операцию
                .findFirst()
                // Что вернуть, если операция будет не возможна
                .orElse(new Person("test", false, 0, AvailableColor.black, AvailableColor.green)))
                ;
        // Сообщает - что в результате операции точно есть результат
        // whenINeedFind.isPresent();
    }

    private void realUse() {
        Stream<Person> newPerson = Stream.of(
                new Person("Boris", false, 10, AvailableColor.blue, AvailableColor.yellow),
                new Person("Sofiya", true, 17, AvailableColor.green, AvailableColor.yellow),
                new Person("Gena", true, 73, AvailableColor.blue, AvailableColor.gray),
                new Person("Sveta", true, 47, AvailableColor.green, AvailableColor.black),
                new Person("Sofiya", true, 17, AvailableColor.green, AvailableColor.yellow)
        );

        Stream.concat(newPerson, people.stream())
                .distinct()
                .skip(1)
                .limit(1)
                .forEach(p->System.out.println(p) );
    }

    private void tryDistinct() {
        people.stream().distinct().forEach(p->System.out.println(p));
    }

    private void joinStreams() {
        Stream<Person> newPerson = Stream.of(
                new Person("Boris", false, 10, AvailableColor.blue, AvailableColor.yellow)
        );

        Stream.concat(newPerson, people.stream()).forEach(p->System.out.println(p) );

    }

    private void withWhile () {
        people.stream()
                .takeWhile(p-> p.getAge()> 20)
                .forEach(p->System.out.println(p) );

        System.out.println("==");

        people.stream()
                .dropWhile(p-> p.getAge()> 20)
                .forEach(p->System.out.println(p) );

    }

    private void streamsMethods() {

        people.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(p->System.out.println(p) );

//        people.stream()
//                .flatMap( p-> Stream.of(
//                        p.getAge(),
//                        p.getName()
//                ))
//                .forEach(item -> System.out.println(((Constable) item).getClass())
//                );



//        // Преобразовывает элемент из потока в другой тип
//        people.stream()
//                .map(p-> p.getName()) // Создать поток имен пиплов
//                .forEach(p-> System.out.println(p));

//        //Применить метод к каждому элементу коллекции
//        people.stream().forEach(p-> {
//            p.setAge(10);
//            System.out.println(p);
//        });
//        echoPeople();
    }



    private void asCount() {

        // Если у меня операции с количеством описаны на CRUD коллекции - и поле хранится отдельно то так лушче
        System.out.println(people.size());

        // В этом случае будет построен поток - и количество элементов будет пересчитано
        System.out.println(people.stream().count());

        // В случае - когда нужно перебрать все элементы до построения ответа - построить поток - боле удобный способ
        System.out.println(
                people.stream() // формирует элементы коллекции в поток
                        .filter(p-> p.getEye()== AvailableColor.blue) // формирует новый поток (промежуточная)
                        .filter(p-> p.getAge() > 60) // из промежуточного потока - формирует новый поток (промежуточная)
                        .count() // в отсееных элементах считает количество - терминальная (окончательная опреация)
        );
    }



//*--------------------------------------------------------------------------------------------------------------
    private ArrayList<Person> people;
    private void buildPeopleList () {
        people = new ArrayList<>();
        people.add(new Person("Sofiya", true, 17, AvailableColor.green, AvailableColor.yellow));
        people.add(new Person("Gena", true, 73, AvailableColor.blue, AvailableColor.gray));
        people.add(new Person("Sveta", true, 47, AvailableColor.green, AvailableColor.black));
        people.add(new Person("Sofiya", true, 17, AvailableColor.green, AvailableColor.yellow));
        people.add(new Person("Sasha", false, 47, AvailableColor.blue, AvailableColor.yellow));
        people.add(new Person("Valya", true, 72, AvailableColor.green, AvailableColor.red));
        people.add(new Person("Artem", true, 18, AvailableColor.gray, AvailableColor.black));

    }

    private void echoPeople() {
        System.out.println("+------------------------------+");
        for (Person p : people) {
            System.out.println(p);
        }
        System.out.println("+------------------------------+");
    }

    public Feb_13() {
        buildPeopleList();
        echoPeople();
    }


}
