package com.itstep.first_spring.dto.websocket.test;

public class GreetingMessageDTO {

	private String content;

	public GreetingMessageDTO() {
	}

	public GreetingMessageDTO(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
