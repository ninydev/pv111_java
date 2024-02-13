package org.itstep.classworks.feb;

import org.itstep.entities.Person;
import org.itstep.entities.types.AvailableColor;

import java.util.ArrayList;

public class Feb_13 implements Runnable
{

    @Override
    public void run() {
        asCount();
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
                        .count() // в отсееных элементах считает количество - терминальная (окончательная опреация
        );
    }



//*--------------------------------------------------------------------------------------------------------------
    private ArrayList<Person> people;
    private void buildPeopleList () {
        people = new ArrayList<>();
        people.add(new Person("Sveta", true, 47, AvailableColor.green, AvailableColor.black));
        people.add(new Person("Sasha", false, 47, AvailableColor.blue, AvailableColor.yellow));
        people.add(new Person("Valya", true, 72, AvailableColor.green, AvailableColor.red));
        people.add(new Person("Gena", true, 73, AvailableColor.blue, AvailableColor.gray));
    }

    private void echoPeople() {
        for (Person p : people) {
            System.out.println(p);
        }
    }

    public Feb_13() {
        buildPeopleList();
        echoPeople();
    }


}
