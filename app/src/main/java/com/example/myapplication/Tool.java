package com.example.myapplication;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tool {
    public static byte[] loadImageBytes(String filePath) throws IOException {

        File file = new File(filePath);
        if(file.exists()){
            file.setReadable(true);
            int y = 1;
        }
        if(file.canRead()){
            System.out.println("可以读取图片");
            int x = 1;
        }

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
