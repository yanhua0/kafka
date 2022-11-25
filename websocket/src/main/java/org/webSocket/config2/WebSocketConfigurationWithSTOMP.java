package org.webSocket.config2;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigurationWithSTOMP implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("*").addInterceptors(httpSessionIdHandshakeInterceptor()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // P2P should conf a /user  ;   broadcast should conf a /topic
       config.enableSimpleBroker("/user","/topic");
//        //这里配置了后台监听,不需要/app连接，后台只需要这么写  @MessageMapping("/v2/chat")
//        config.setApplicationDestinationPrefixes("/app");   // Client to Server
        //这里配置stompClient.subscribe的user，后台使用了convertAndSendToUser
         config.setUserDestinationPrefix("/user");           // Server to Client
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        //设置消息输入通道的线程池线程数
        registration.taskExecutor()
                .corePoolSize(4)
                //最大线程数
                .maxPoolSize(8)
                //线程活动时间
                .keepAliveSeconds(60);
        registration.interceptors(presenceChannelInterceptor());
    }

    /**
     * 输出通道参数设置
     */
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        registration.taskExecutor()
//                .corePoolSize(4)
//                .maxPoolSize(8);
//        registration.interceptors(presenceChannelInterceptor());
//    }
    public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
        return new HttpSessionIdHandshakeInterceptor();
    }
    public PresenceChannelInterceptor presenceChannelInterceptor() {
        return new PresenceChannelInterceptor();
    }
}