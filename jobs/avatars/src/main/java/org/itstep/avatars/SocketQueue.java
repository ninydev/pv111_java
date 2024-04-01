package org.itstep.avatars;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketQueue {
    @Bean
    public Queue notifications() {
        return new Queue("notifications", true);
    }
}
