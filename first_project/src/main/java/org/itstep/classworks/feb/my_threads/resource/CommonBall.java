package org.itstep.classworks.feb.my_threads.resource;

import lombok.Data;
import org.itstep.entities.types.AvailableColor;

@Data
public class CommonBall
{
    private AvailableColor color = AvailableColor.yellow;
    private int count = 0;
}
