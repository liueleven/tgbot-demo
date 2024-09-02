package org.example.command;
import org.example.config.ConfigLoader;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScope;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

public class CommandBot extends TelegramLongPollingBot {

    public CommandBot(DefaultBotOptions options) {
        super(options);
        setCommands();
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
        // 处理更新
//        System.out.println(update);
        getCommands();
    }

    public void setCommands() {
        // 创建命令列表
        BotCommand[] commands = {
                new BotCommand("start", "Start the bot"),
                new BotCommand("help", "Get help information"),
                new BotCommand("echo", "Echo your message")
        };

        // 创建 SetMyCommands 请求
        BotCommandScope scope = new BotCommandScopeDefault();
        SetMyCommands setMyCommands = new SetMyCommands(Arrays.asList(commands), scope, "en");

        try {
            // 发送请求
            execute(setMyCommands);
            System.out.println("命令已设置成功！");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.err.println("设置命令失败: " + e.getMessage());
        }
    }


    public void getCommands() {
        try {
            GetMyCommands getMyCommands = new GetMyCommands(new BotCommandScopeDefault(), "en");
            List<BotCommand> commands = execute(getMyCommands);
            for (BotCommand command : commands) {
                System.out.println("Command: " + command.getCommand() + " - " + command.getDescription());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
