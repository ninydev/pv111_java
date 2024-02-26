package org.itstep.classworks.feb.my_threads.for_wait;

public class Producer implements Runnable
{
    private final Store store;
    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            store.putMails();
        }
    }
}
