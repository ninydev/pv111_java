package org.itstep.classworks.feb;

import org.itstep.classworks.feb.my_threads.MyFirstThread;
import org.itstep.classworks.feb.my_threads.MyLoopThread;
import org.itstep.classworks.feb.my_threads.MySecondThread;

public class Feb_22 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Class Work ThreadName: " + Thread.currentThread().getName() + "\n\n");
        try {
            asLoop();
        } catch (InterruptedException e) {
            System.out.println(" Мы прервали поток ");
            // throw new RuntimeException(e);
        }
    }

    private void asLoop() throws InterruptedException {
        MyLoopThread l = new MyLoopThread();
        Thread t = new Thread(l);
        t.start();

        Thread.sleep(2000);

        t.interrupt();



    }


    private void asChildren() {
        MySecondThread t = new MySecondThread("As Children");
        t.start();
    }


    private void asInterface() {
        // Вариант запуска не правильный
        MyFirstThread myThread = new MyFirstThread("In Main");
        myThread.run();

        // Вариант запуска правильный
        Thread t = new Thread(myThread);
        t.start();

        (
                new Thread(new Runnable()
                {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
                )
        ).start();
    }
}
