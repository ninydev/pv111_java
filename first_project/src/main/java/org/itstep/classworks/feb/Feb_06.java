package org.itstep.classworks.feb;

import org.itstep.entities.User;

public class Feb_06 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Class Work " + this.getClass());
        createUser();
    }

    private void createUser() {
        User u = new User();
        u.setName("Oleksandr");
        u.setEmail("keeper@ninydev.com");
        u.setPassword("QweAsdZxc!23");

        System.out.println(u);
    }
}
