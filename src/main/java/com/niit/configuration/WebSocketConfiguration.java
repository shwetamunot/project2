package com.niit.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.niit")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// js stomp.over("/chatmodule")
        System.out.println("Register endpoints");
		registry.addEndpoint("/chatmodule").withSockJS();	
	}

	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
	
	}

	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}
	public void configureMessageBroker(MessageBrokerRegistry registers) {
		//enablebroker destination prefixes-by spring controller to send message to client
		//to send data from server to client
		//topic- to  notify  newly joined username
		//queue- to send chat message
		//communication direction- from server to client 
		//server user destination  /queue /topic to send message to the client
		//client will receive the message by subscribing $scope.subscribe("/topic/join",...) 
		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY");
		registers.enableSimpleBroker("/queue/", "/topic/");
		//client to server- /app
		//in client side ..send("/app/join",..)
		registers.setApplicationDestinationPrefixes("/app");
	}

	

}
