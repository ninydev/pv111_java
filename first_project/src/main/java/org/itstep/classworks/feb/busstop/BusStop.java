package org.itstep.classworks.feb.busstop;

import java.util.ArrayDeque;
import java.util.Random;

public class BusStop implements Runnable
{
    private int peopleStart = 10;
    private int peopleInMin = 10;
    private int peopleInMax = 10;
    private int busMin = 10;
    private int busMax = 10;
    private int timeTest = 10;
    private int workerRait = 10;

    private ArrayDeque<Passenger> passengers = new ArrayDeque<>();

    private int  waitAll = 0;
    private int  totalPassengers = 0;

    Random random = new Random();

    /**
     * Получение случайного числа в нужном мне диапазоне
     * @param min
     * @param max
     * @return
     */
    private int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    /**
     * Пассажиры приходят на оставновку
     * @param t
     */
    private void inPassengers(int t) {
        int c = getRandom(peopleInMin, peopleInMax);
        for (int i = 0; i < c; i++) {
            passengers.push(new Passenger(t));
        }
    }

    /**
     * Пассажиры садяться в автобус
     * @param t
     */
    private void outPassengers(int t) {
        int c = getRandom(busMin, busMax);
        for (int i = 0; i < c; i++) {
            waitAll+= t - passengers.pop().getIncomingTime();
            totalPassengers++;
        }
    }


    /**
     * Первоначальное наполнение остановки
     */
    private void beforeStart() {
        for (int i = 0; i < peopleStart; i++) {
            passengers.push(new Passenger(0));
        }
    }


    /**
     * Процесс проведения эксперемента
     */
    @Override
    public void run() {
        beforeStart();
        for (int t = 0; t < timeTest; t++) {
            inPassengers(t);
            outPassengers(t);
        }
        outStat();
    }

    private void outStat() {
        System.out.println("Время эксперемента \t" + timeTest);
        System.out.println("Было перевезено \t" + totalPassengers);
        System.out.println("Не попали на работу \t" + passengers.size());
        System.out.println("Среднее время ожидания \t" + waitAll/totalPassengers);
        double d = workerRait * waitAll / 60 + passengers.size() * 8 * workerRait;
        System.out.println("Убыток экономики" + d);
    }
}
