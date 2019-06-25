package by.alexkasko.bytouristbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "bot")
public class TelegramBotProperties {

    @NotNull
    private String name;

    @NotNull
    private String token;


    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
