package org.itstep.classworks.feb.my_threads.for_wait;

import lombok.Data;


public class Store
{
    private int nails = 10;

    // Сообщаю складу - что нужно отминисовать гвозди на складе
    public synchronized void minusNails() {
        try {
            while (nails < 1) {
                // Если гвоздей меньше - чем 1 - ждать всем новых гвоздей
                wait();
            }
        } catch (Exception e) {}

        // Если гвоздей стало больше - отминисувать и
        // СООБЩИТЬ всем - что можно продолжить работу
        nails--;
        notifyAll();
        System.out.println("-- Со склада списали гвоздь - остаток: " + nails);
    }

    public synchronized void putMails() {
        try{
        while (nails > 11) {
            // Если гвоздей много - не пускать их на склад
            wait();
        }
    } catch (Exception e) {}

        nails = nails + 5;
        notify();
        System.out.println("++ Прибыли гвозди - Остаток: " + nails);

    }

}
