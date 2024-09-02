package org.example.handle;

import org.example.enums.MessageType;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @date: 2024-09-02 12:23
 * @author: 十一
 */
public class MessageHandlerFactory {

    private static final Map<MessageType, MessageHandler> handlers = new HashMap<>();

    // 注册消息处理器
    public static void registerHandler(MessageType messageType, MessageHandler handler) {
        handlers.put(messageType, handler);
    }

    // 根据消息类型获取对应的处理器
    public static MessageHandler getHandler(MessageType messageType) {
        return handlers.get(messageType);
    }
}
