package org.itstep;

import org.itstep.classworks.feb.Feb_06;
import org.itstep.classworks.feb.Feb_08;
import org.itstep.classworks.feb.Feb_12;
import org.itstep.classworks.feb.Feb_13;
import org.itstep.classworks.feb.busstop.BusStop;

public class Main {
    public static void main(String[] args) {

//        BusStop busStop = new BusStop();
//        try {
//            busStop.run();
//        } catch (IllegalArgumentException e){
//            System.out.println("Error");
//        }


        Runnable classWork = new Feb_13();
        classWork.run();

        System.out.println("App finish");
    }
}