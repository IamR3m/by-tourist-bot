package by.alexkasko.bytouristbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableJpaAuditing
public class BytouristbotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
/*		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new TelegramBot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}*/
		SpringApplication.run(BytouristbotApplication.class, args);
	}

}