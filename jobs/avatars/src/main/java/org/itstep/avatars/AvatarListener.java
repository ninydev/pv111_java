package org.itstep.avatars;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvatarListener {
    @Bean
    public Queue avatars() {
        return new Queue("avatars", true);
    }
    @RabbitListener(queues = "avatars")
    public void listen(String in) {
        System.out.println("Message read from avatars : " + in);
    }
}
