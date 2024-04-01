package com.itstep.first_spring.config.websocket;

import com.itstep.first_spring.services.auth.JwtService;
import com.itstep.first_spring.services.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocketSecurity
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// Для публичных (всех)
		registry.addEndpoint("/websocket-public");
		registry.addEndpoint("/websocket-public").withSockJS();;
		// Для частных (приватных)
		registry.addEndpoint("/websocket-private");
		registry.addEndpoint("/websocket-private").withSockJS();;
	}

	private final JwtService jwtService;
	private final UserService userService;

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				return message;

//				try {
//					StompHeaderAccessor accessor =
//							MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//					// log.info("Headers: {}", accessor);
//
//					assert accessor != null;
//					if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//
//						String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
//						assert authorizationHeader != null;
//						System.out.println("authorizationHeader: " + authorizationHeader);
//						String token = authorizationHeader.substring(7);
//
//						String username = jwtService.extractUserName(token);
//						UserDetails userDetails = userService.getByUsername(username);
//						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//								= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//						SecurityContextHolder.getContext()
//								.setAuthentication(usernamePasswordAuthenticationToken);
//
//						accessor.setUser(usernamePasswordAuthenticationToken);
//					}
//				} catch (Exception e) {
//
//				}
//
//
//				return message;
			}

		});
	}



}
