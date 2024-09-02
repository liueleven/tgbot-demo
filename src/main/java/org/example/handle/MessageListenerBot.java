package org.example.handle;

import org.example.config.ConfigLoader;
import org.example.enums.MessageType;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @description:
 * @date: 2024-09-01 23:41
 * @author: liuziqing
 */
public class MessageListenerBot extends TelegramLongPollingBot {


    public MessageListenerBot(DefaultBotOptions options) {
        super(options);
    }


    @Override
    public String getBotUsername() {
        return ConfigLoader.getUserName();
    }

    @Override
    public String getBotToken() {
        return ConfigLoader.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        // 需要实现 determineMessageType 方法
        MessageType messageType = MessageType.getMessageType(update);
        // 从工厂中获取对应的处理器并处理消息
        MessageHandler handler = MessageHandlerFactory.getHandler(messageType);
        if (handler != null) {
            handler.handle(update, this);
        } else {
            System.out.println("未找到对应的消息处理器: " + messageType);
        }
    }
}
