package org.itstep.classworks.feb.my_threads;

import org.itstep.classworks.feb.my_threads.resource.CommonBall;
import org.itstep.entities.types.AvailableColor;

public class FootballManThread implements Runnable
{
    AvailableColor color;
    CommonBall ball;

    public FootballManThread(AvailableColor color, CommonBall ball) {
        this.color = color;
        this.ball = ball;
    }

    @Override
    public void run() {

    }
}
