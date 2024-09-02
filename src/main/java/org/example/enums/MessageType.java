package org.example.enums;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @description:
 * @date: 2024-09-02 12:33
 * @author: 十一
 */
public enum MessageType {
    TEXT("text") {
        @Override
        public boolean matches(Update update) {
            return update.hasMessage() && update.getMessage().hasText();
        }
    },
    PHOTO("photo") {
        @Override
        public boolean matches(Update update) {
            return update.hasMessage() && update.getMessage().hasPhoto();
        }
    };

    private final String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // 每个枚举值都必须实现此方法，用于判断是否匹配
    public abstract boolean matches(Update update);

    // 从 Update 对象中确定消息类型
    public static MessageType getMessageType(Update update) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.matches(update)) {
                return messageType;
            }
        }
        return null; // 没有匹配到时返回 null
    }

}
