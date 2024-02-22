package org.itstep.classworks.feb.my_threads;

public class MyFirstThread implements Runnable
{
    private String message;
    public MyFirstThread(String message) {
        this.message = message;
    }


    @Override
    public void run() {
        System.out.println("ThreadName: " + Thread.currentThread().getName() + "\t" + message);
    }
}
