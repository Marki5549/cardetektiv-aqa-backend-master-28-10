package de.cardetektiv.app.config;

import de.cardetektiv.app.websocket.WebSocketClientFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfiguration {

    @Bean
    public WebSocketClientFacade webSocketClientFacade(@Value("${web.socket.base.url}") String wsBaseUrl) {
        return new WebSocketClientFacade(wsBaseUrl);
    }
}
