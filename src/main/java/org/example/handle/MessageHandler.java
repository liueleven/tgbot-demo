package org.example.handle;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * @description: 一定要写注释啊
 * @date: 2024-09-02 12:28
 * @author: 十一
 */
public interface MessageHandler {
//    void handle(Update update);
    void handle(Update update, MessageListenerBot bot);
}
