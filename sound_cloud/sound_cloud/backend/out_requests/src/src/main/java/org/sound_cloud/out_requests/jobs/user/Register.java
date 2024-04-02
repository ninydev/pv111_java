package org.sound_cloud.out_requests.jobs.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.sound_cloud.out_requests.dto.auth.UserDTO;
import org.sound_cloud.out_requests.services.GravatarService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;


@Configuration
@AllArgsConstructor
public class Register {

    private final GravatarService gravatarService;


    @Bean
    public Queue registerListenQueue() {
        return new Queue("avatars", true);
    }
    @RabbitListener(queues = "user.register")
    public void registerListen(Message message) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Start");
        String json = new String(message.getBody());
        UserDTO userDTO = objectMapper.readValue(json, UserDTO.class);
        System.out.println("Message read from register : " + userDTO);

        System.out.println("Message read from register : " + gravatarService.getGravatarUrl(userDTO.getEmail()));


    }

}
