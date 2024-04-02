package org.sound_cloud.api.rabbitmq.auth;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RegisterEvent {
    @Bean
    public Queue register() {
        return new Queue("user.register", true);
    }

}
