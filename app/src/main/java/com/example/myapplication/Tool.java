package com.example.myapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Tool {
    public static byte[] loadImageBytes(String imagePath) {
        byte[] imageBytes = null;

        try {
            File imageFile = new File(imagePath);
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            int length = (int) imageFile.length();
            imageBytes = new byte[length];

            int bytesRead = fileInputStream.read(imageBytes, 0, length);

            if (bytesRead != length) {
                // 处理读取图像数据时的错误
            }

            fileInputStream.close();
        }
        catch (IOException e) {
            // 处理文件读取或其他异常
        }
        return imageBytes;
    }

}
