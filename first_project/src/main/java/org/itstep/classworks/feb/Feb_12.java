package org.itstep.classworks.feb;

import org.itstep.entities.Role;
import org.itstep.entities.User;

import java.util.*;

public class Feb_12 implements Runnable
{
    @Override
    public void run() {
        mapSet();
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
