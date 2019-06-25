package by.alexkasko.bytouristbot.component;


import by.alexkasko.bytouristbot.config.TelegramBotProperties;
import by.alexkasko.bytouristbot.model.City;
import by.alexkasko.bytouristbot.repository.CityRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private CityRepository cityRepository;

    private Logger logger;

    @Autowired
    private TelegramBotProperties telegramBotProperties;

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText() && "/start".equals(message.getText())) {
                sendMsg(message,"Я могу рассказывать что посетить в каком-либо городе.\n"+
                        "Просто пришлите мне название города", false);
                return;
            }

            if (!message.hasText()) {
                sendMsg(message,"Я не понимаю что вы мне прислали. Напишите название города.");
                return;
            }
            String msg = message.getText().toLowerCase();
            if (msg.split(" ").length > 2) {
                sendMsg(message,"Кажется, то, что вы мне написали никак не может быть названием города");
                return;
            }
            City city  = cityRepository.findAll().stream()
                    .filter(c-> c.getName().toLowerCase().equals(msg.toLowerCase()))
                    .findFirst().orElse(null);
            if (city == null) {
                sendMsg(message,"Не могу найти город у себя в базе, я еще не такой умный");
            } else {
                sendMsg(message,city.getDescription());
            }
        }
    }

    private Message sendMsg(Message message, String text) {
        return sendMsg(message, text, true);
    }

    private Message sendMsg(Message message, String text, boolean isReply){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        if (isReply) {
            sendMessage.setReplyToMessageId(message.getMessageId());
        }
        sendMessage.setText(text);

        try {
            return execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("sendMsg ERROR! text: " + text, e);
        }
        return null;
    }

}
