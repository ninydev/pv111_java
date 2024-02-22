package org.itstep.classworks.feb.my_threads;

public class MyLoopThread implements Runnable
{
    private int count = 0;
    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted() ) {
            System.out.println(Thread.currentThread().getName() + " " + (count++) );
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.out.println(" Мы прервали поток - я узнал об этом внутри - когда спал");
//                throw new RuntimeException(e);
//            }
        }

        System.out.println("А тут я типа сам умный и вышел - когда сказали выходить");


//        while (true) {
//            System.out.println(Thread.currentThread().getName() + " " + (count++) );
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.out.println(" Мы прервали поток - я узнал об этом внутри - когда спал");
//                throw new RuntimeException(e);
//            }
//        }
    }
}
