package com.wmltyq.qrcode;


import com.swetake.util.Qrcode;
import com.wmltyq.util.WriteFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CreateQRCode {
    public static void main(String[] args) throws IOException {
        // 定义二维码保存路径
        String output = "output/";
        String[] codeArgs = new WriteFile().main();
        if (codeArgs == null) {
            return;
        }
        // 待编码字符串
        String content = codeArgs[0];
        // 二维码图片名
        String imgName = codeArgs[1];
        // 二维码图片格式
        String format = codeArgs[2];

        Qrcode x = new Qrcode();
        // 设置纠错等级
        x.setQrcodeErrorCorrect('M');
        // N代表数字，A代表a-Z，B代表其他字符
        x.setQrcodeEncodeMode('B');
        // 版本
        x.setQrcodeVersion(7);

        // 设置二维码的宽高
        int width = 67 + 12 * (7 - 1);
        int height = 67 + 12 * (7 - 1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);

        // 偏移量
        int pixoff = 2;

        byte[] d = content.getBytes("utf-8");
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }

        gs.dispose();
        bufferedImage.flush();

        ImageIO.write(bufferedImage, format, new File(output + imgName + "." + format));
    }
}
