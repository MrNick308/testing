package ru.dante.work.checking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.dante.work.checking.yandexapi.YandexCheckService;

@Configuration
@ComponentScan("ru.dante.work.checking.yandexapi")
public class CheckTestConfig {
    @Bean
    public YandexCheckService getYandexCheckService() {
        return new YandexCheckService();
    }
}
