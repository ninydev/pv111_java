package com.itstep.first_spring.controllers.websocket;

import com.itstep.first_spring.dto.websocket.test.GreetingMessageDTO;
import com.itstep.first_spring.dto.websocket.test.HelloMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public GreetingMessageDTO greeting(HelloMessageDTO message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new GreetingMessageDTO("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}
