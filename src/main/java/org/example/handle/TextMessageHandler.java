package org.example.handle;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @description:
 * @date: 2024-09-02 12:28
 * @author: liuziqing
 */
public class TextMessageHandler implements MessageHandler {
    @Override
    public void handle(Update update, MessageListenerBot bot) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        // 创建返回消息
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageText); // 回声功能，原样返回收到的消息

        try {
            bot.execute(message); // 发送消息
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
