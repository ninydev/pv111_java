package org.itstep.classworks.feb.my_threads.for_exchange;

import java.util.concurrent.Exchanger;

public class GetExchange implements Runnable
{
    private final Exchanger<String> exchanger;

    public GetExchange(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        String messageFromGet = "From Get Exchange";
        try{
            String messageFromPut = exchanger.exchange(messageFromGet);

            System.out.println(" Get: "
                    + "\n messageFromPut " + messageFromPut
                    + "\n messageFromGet " + messageFromGet);
        } catch (Exception e) {

        }
    }
}
