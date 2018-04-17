package com.wmltyq.util;

import java.util.Scanner;

// 读取二维码开头公共处理逻辑
public class WriteFile {
    public String[] main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您要编码的文本：");
        String content = scanner.nextLine();
        System.out.print("请输入二维码图片名称：");
        String imgName = scanner.nextLine();
        System.out.print("请输入二维码图片格式：");
        String format = scanner.nextLine();
        while (!("jpg".equals(format) || "png".equals(format))) {
            System.out.print("输入的二维码图片格式有问题，请重新输入【输入quit退出程序】：");
            format = scanner.nextLine();
            if ("quit".equals(format)) {
                return null;
            }
        }
        return new String[]{content, imgName, format};
    }
}
