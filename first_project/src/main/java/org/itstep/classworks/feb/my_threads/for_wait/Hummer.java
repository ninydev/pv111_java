package org.itstep.classworks.feb.my_threads.for_wait;

public class Hummer implements Runnable
{
    private final Store store;
    public Hummer(Store store) {
        this.store = store;
    }
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            store.minusNails();
        }

    }
}
