package org.itstep.classworks.feb;

import org.itstep.classworks.feb.my_threads.MyFirstThread;

public class Feb_22 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Class Work ThreadName: " + Thread.currentThread().getName() + "\n\n");

        // Вариант запуска не правильный
        MyFirstThread myThread = new MyFirstThread("In Main");
        myThread.run();

        // Вариант запуска правильный
        Thread t = new Thread(myThread);
        t.start();

        (new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        })).start();
    }
}
