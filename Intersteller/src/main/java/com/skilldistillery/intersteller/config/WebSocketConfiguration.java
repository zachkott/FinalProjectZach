package com.skilldistillery.intersteller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override //sets entry points
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/rfpchat").setAllowedOriginPatterns("*").withSockJS();
	}
	
	@Override //app destination prefixes to allow for unique message streams
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
	}
}

