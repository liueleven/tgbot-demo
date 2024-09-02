package org.example;

import org.example.command.CommandBot;
import org.example.config.ConfigLoader;
import org.example.enums.MessageType;
import org.example.handle.*;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {


    public static void main(String[] args) {
        System.out.println(String.format("start name: %s token: %s",  ConfigLoader.getUserName(),  ConfigLoader.getToken()));

        // 设置代理选项
        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost("127.0.0.1");
        botOptions.setProxyPort(7890);
        // 根据需要可以选择 HTTP 或 SOCKS5
        botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
        /**====================================*/
        // 注册各种消息处理器
        MessageHandlerFactory.registerHandler(MessageType.TEXT, new TextMessageHandler());
        MessageHandlerFactory.registerHandler(MessageType.PHOTO, new PhotoMessageHandler());




        // 启动 bot
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MessageListenerBot(botOptions));
            botsApi.registerBot(new CommandBot(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}