package etecc.cac.cac_api.error;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class TelegramNotificationError extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = System.getenv("CAC_TOKEN_BOT");
    private static final Long DEF_USER_ID = Long.valueOf(System.getenv("TELEGRAM_USER_ID"));

    @Override
    public String getBotUsername() {
        return "Caribbean_cbt_bot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


    @Override
    public String getBotToken() {
        return BOT_TOKEN;

    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    public void notifyError(String msg) {

        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(DEF_USER_ID);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            LogFile.writeLogError(e,false);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e,() -> "");
        }

    }
}