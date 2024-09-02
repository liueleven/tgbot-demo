package org.example.handle;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description:
 * @date: 2024-09-02 12:28
 * @author: liuziqing
 */
public class PhotoMessageHandler implements MessageHandler {
    @Override
    public void handle(Update update, MessageListenerBot bot) {
        // 获取照片列表中的最高分辨率照片（列表按分辨率从低到高排序）
        Message message = update.getMessage();
        String fileId = message.getPhoto().get(message.getPhoto().size() - 1).getFileId();

        // 通过文件 ID 获取文件路径
        try {
            GetFile getFile = new GetFile();
            getFile.setFileId(fileId);
            File file = bot.execute(getFile);
            System.out.println("File path: " + file.getFileUrl(bot.getBotToken()));
            // 下载文件
            String fileUrl = "https://api.telegram.org/file/bot" + bot.getBotToken() + "/" + file.getFilePath();
            java.io.File file1 = downloadImage(fileUrl);

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(String.valueOf(message.getChatId()));
            sendPhoto.setPhoto(new InputFile(file1).setMedia(file1));
            bot.execute(sendPhoto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static java.io.File downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 检查响应码
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("下载失败，响应码: " + connection.getResponseCode());
        }

        // 创建临时文件
        java.io.File tempFile = java.io.File.createTempFile("telegram_image", ".jpg");

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return tempFile;
    }
}
