package org.itstep.classworks.feb.my_threads;

import org.itstep.classworks.feb.my_threads.resource.CommonBall;
import org.itstep.entities.types.AvailableColor;

import java.util.concurrent.Semaphore;

public class FootballManThread implements Runnable
{
    // Цвет формы футболиста
    AvailableColor color;

    // Ссыка на общий ресурс
    final CommonBall ball;
    final Semaphore sem;

    public FootballManThread(AvailableColor color, CommonBall ball) {
        this.color = color;
        this.ball = ball;
        sem = null;
    }

    public FootballManThread(AvailableColor color, CommonBall ball, Semaphore sem) {
        this.color = color;
        this.ball = ball;
        this.sem = sem;
    }
    @Override
    public void run() {
//        synchronized (ball) {
//            operation();
//        }

        try {
            sem.acquire();
            operation();
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    private void operation() {
        // Я еще не бил по мячику - я сбрасываю счетчик
        ball.setCount(0);
        // Я говорю, что я буду бить по мячику
        ball.setColor(color);

        for (int i = 0; i < 5; i++) {
            // Я ударил 1 раз
            ball.setCount(ball.getCount() + 1);
            System.out.println(" FootballMan (" + i + ") "
                    + color + ": "
                    + ball.getCount() + " / ballColor: "
                    + ball.getColor()
            );
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
