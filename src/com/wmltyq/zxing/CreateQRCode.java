package com.wmltyq.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wmltyq.util.WriteFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

// 生成二维码
public class CreateQRCode {
    public static void main(String[] args) {
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

        // 二维码的长宽
        int width = 300;
        int height = 300;

        // 定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 边距设置
        hints.put(EncodeHintType.MARGIN, 2);

        // 生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(output + imgName + "." + format).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
