package org.itstep.classworks.feb;

import org.itstep.classworks.feb.my_threads.FootballManThread;
import org.itstep.classworks.feb.my_threads.for_exchange.GetExchange;
import org.itstep.classworks.feb.my_threads.for_exchange.PutExchange;
import org.itstep.classworks.feb.my_threads.for_wait.Hummer;
import org.itstep.classworks.feb.my_threads.for_wait.Producer;
import org.itstep.classworks.feb.my_threads.for_wait.Store;
import org.itstep.classworks.feb.my_threads.resource.CommonBall;
import org.itstep.entities.types.AvailableColor;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Feb_26 implements Runnable
{
    @Override
    public void run() {

        System.out.println("Class Work ThreadName: " + Thread.currentThread().getName() + "\n\n");

        doPull();

    }

    private void doPull() {
        CommonBall ball = new CommonBall();

        FootballManThread f1 = new FootballManThread(AvailableColor.blue, ball);
        FootballManThread f2 = new FootballManThread(AvailableColor.red, ball);
        FootballManThread f3 = new FootballManThread(AvailableColor.green, ball);

        try{
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    1, // Размер пула потоков
                    1, // Максимальное количество потоков
                    0L, // Время ожидания перед завершением неиспользуемых потоков
                    TimeUnit.MILLISECONDS, // Единица измерения времени
                    new LinkedBlockingQueue<>() // Очередь задач
            );
            executor.execute(f1);
            executor.execute(f2);
            executor.execute(f3);

            executor.shutdownNow();

        } catch (Exception e) {}




    }


    private void doStore() {
        Store store = new Store();
        Hummer hummer = new Hummer(store);
        Thread th = new Thread(hummer);
        th.start();

        Producer producer = new Producer(store);
        Thread tp = new Thread(producer);
        tp.start();

        try {
            th.join();
            tp.join();
        } catch (Exception e) {}


    }

    private void doExchange() {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread t1 = new Thread( new PutExchange(exchanger));
        t1.start();
        Thread t2 = new Thread( new GetExchange(exchanger));
        t2.start();

        Thread t3 = new Thread( new GetExchange(exchanger));
        t3.start();

        Thread t4 = new Thread( new GetExchange(exchanger));
        t4.start();

        Thread t5 = new Thread( new GetExchange(exchanger));
        t5.start();
        try {
            t5.join();
        } catch (Exception e) {

        }


    }

    private void doMansSemaphore(){
        CommonBall ball = new CommonBall();
        Semaphore sem = new Semaphore(1);

        FootballManThread f1 = new FootballManThread(
                AvailableColor.blue, ball, sem);
        Thread thread1 = new Thread(f1);
        thread1.start();

        FootballManThread f2 = new FootballManThread(AvailableColor.red, ball, sem);
        Thread thread2 = new Thread(f2);
        thread2.start();

        FootballManThread f3 = new FootballManThread(AvailableColor.green, ball, sem);
        Thread thread3 = new Thread(f3);
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void doMansSynchronized(){
        CommonBall ball = new CommonBall();

        FootballManThread f1 = new FootballManThread(AvailableColor.blue, ball);
        Thread thread1 = new Thread(f1);
        thread1.start();

        FootballManThread f2 = new FootballManThread(AvailableColor.red, ball);
        Thread thread2 = new Thread(f2);
        thread2.start();

        FootballManThread f3 = new FootballManThread(AvailableColor.green, ball);
        Thread thread3 = new Thread(f3);
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void doFootballMan () {
        CommonBall ball = new CommonBall();
        FootballManThread t1 = new FootballManThread(AvailableColor.blue, ball);
        Thread thread = new Thread(t1);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println("Error join");
        }

    }
}
