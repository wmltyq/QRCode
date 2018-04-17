package com.wmltyq.qrcode;

import com.wmltyq.util.ReadFile;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ReadQRCode {
    public static void main(String[] args){
        // 二维码存放路径
        String path = "output/";
        File file = new ReadFile(path).main();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("文件读取失败！");
            return;
        }
        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        String result = null;
        try {
            result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
        } catch (UnsupportedEncodingException e) {
            // System.out.println("二维码未识别！");
        } catch (DecodingFailedException e) {
            System.out.println("二维码未识别！");
            return;
        }

        System.out.println("二维码文本内容：" + result);
    }
}
