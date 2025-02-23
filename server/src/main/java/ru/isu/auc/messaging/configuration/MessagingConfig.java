package ru.isu.auc.messaging.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.isu.auc.messaging.service.MessagingService;
import ru.isu.auc.messaging.service.MessagingServiceImpl;

@Configuration
public class MessagingConfig {
    @Bean
    public MessagingService messagingService() {
        return new MessagingServiceImpl();
    }
}
