package org.itstep.classworks.feb.my_threads.for_exchange;

import java.util.concurrent.Exchanger;

public class PutExchange implements Runnable
{
    private final Exchanger<String> exchanger;

    public PutExchange(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        String messageFromPut = "From Put Exchange";
         try{
             String messageFromGet = exchanger.exchange(messageFromPut);

             System.out.println(" Put: "
                     + "\n messageFromPut " + messageFromPut
                     + "\n messageFromGet " + messageFromGet);
         } catch (Exception e) {

         }

    }
}
