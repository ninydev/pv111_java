package org.itstep.classworks.feb;

import org.itstep.entities.Role;
import org.itstep.entities.User;

public class Feb_06 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Class Work " + this.getClass());
        createUserAndRoles();
    }

    private void createUserAndRoles() {
        Role writer = new Role("Writer");
        Role reader = new Role("Reader");

        User u = new User();
        u.setName("Oleksandr");
        u.setEmail("keeper@ninydev.com");
        u.setPassword("QweAsdZxc!23");

        u.getRoles().add(writer);
        writer.getUsers().add(u);

        u.getRoles().add(reader);
        reader.getUsers().add(u);

        System.out.println(u);

//        try {
//            System.out.println(u);
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
    }





    /**
     * Процесс создания сущности пользователь
     */

    private void createUser() {
        User u = new User();
        u.setName("Oleksandr");
        u.setEmail("keeper@ninydev.com");
        u.setPassword("QweAsdZxc!23");

        System.out.println(u);
    }
}
