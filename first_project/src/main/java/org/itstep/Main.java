package org.itstep;

import org.itstep.classworks.feb.*;
import org.itstep.classworks.feb.busstop.BusStop;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        BusStop busStop = new BusStop();
//        try {
//            busStop.run();
//        } catch (IllegalArgumentException e){
//            System.out.println("Error");
//        }


        Runnable classWork = new Feb_22();
        classWork.run();

        Thread.sleep(1000);
        System.out.println("\n\n+------------------------------+");
        System.out.println("App finish");
    }
}