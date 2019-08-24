package org.webSocket.config2;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigurationWithSTOMP implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // P2P should conf a /user  ;   broadcast should conf a /topic
        config.enableSimpleBroker("/topic", "/queue", "/user");
        //这里配置了后台监听,不需要/app连接，后台只需要这么写  @MessageMapping("/v2/chat")
        config.setApplicationDestinationPrefixes("/app");   // Client to Server
        //这里配置stompClient.subscribe的user，后台使用了convertAndSendToUser
        config.setUserDestinationPrefix("/user");           // Server to Client
    }
}