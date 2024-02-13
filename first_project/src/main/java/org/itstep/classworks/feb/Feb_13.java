package org.itstep.classworks.feb;

import org.itstep.entities.Person;
import org.itstep.entities.types.AvailableColor;

import java.util.ArrayList;

public class Feb_13 implements Runnable
{

    @Override
    public void run() {

    }


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
