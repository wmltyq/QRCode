package com.wmltyq.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.wmltyq.util.ReadFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

// 读取二维码
public class ReadQRCode {
    public static void main(String[] args) throws IOException {
        // 二维码存放路径
        String path = "output/";
        File f = new ReadFile(path).main();

        MultiFormatReader formatReader = new MultiFormatReader();
        BufferedImage image = ImageIO.read(f);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        // 定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        Result result = null;
        try {
            result = formatReader.decode(binaryBitmap, hints);
        } catch (NotFoundException e) {
            System.out.println("二维码未识别！");
            return;
        }
        // System.out.println("解析结果：" + result.toString());
        System.out.println("二维码格式类型：" + result.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result.getText());
    }
}
