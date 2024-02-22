package org.itstep.classworks.feb.my_threads;

public class MySecondThread extends Thread
{
    private String message;
    public MySecondThread(String message) {
        this.message = message;
    }


    @Override
    public void run() {
        System.out.println("ThreadName: " + Thread.currentThread().getName() + "\t" + message);
    }
}
