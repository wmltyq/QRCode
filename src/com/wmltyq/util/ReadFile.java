package com.wmltyq.util;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class ReadFile {
    private String path;

    public ReadFile(String path) {
        this.path = path;
    }

    // 判断文件夹是否存在
    public File isExist() {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path + "文件夹不存在，请输入二维码存放的正确文件路径！");
            return null;
        }
        return file;
    }

    // 打印指定文件夹下的所有文件
    public File[] getFilesName() {
        File file = isExist();
        File[] files = null;
        if (file != null) {
            files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹为空，请在该文件夹下放入二维码图片！");
                return null;
            }
            System.out.println(path + "文件夹下有以下图片文件：");
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                System.out.println(">>> " + fileName);
            }
        }
        return files;
    }

    // 读取指定目录并获取指定文件的后缀名
    public String getSuffix(File[] files, String imgName) {
        // 存储文件名
        String[] filesName = new String[10];
        // 存储文件后缀名
        String[] suffixes = new String[10];
        String[] fileNameSplit = new String[2];
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            fileNameSplit = fileName.split("\\.");
            filesName[i] = fileNameSplit[0];
            suffixes[i] = fileNameSplit[1];
        }
        String suffix = null;
        if (Arrays.asList(filesName).contains(imgName)) {
            // 当filesName没有填充满容易报NullPointerException异常
            // int index = Arrays.binarySearch(filesName, imgName);
            // 所以改用以下方法
            int index = Arrays.asList(filesName).indexOf(imgName);
            suffix = suffixes[index];
        }
        return suffix;
    }

    public File main() {
        File[] files = getFilesName();
        if (files == null) {
            return null;
        }

        System.out.print("\n请从以上文件中输入您要解码的图片文件名【不需要输入文件后缀名】：");
        Scanner scanner = new Scanner(System.in);
        String imgName = scanner.nextLine();
        String suffix = null;
        // 获取指定文件的后缀名
        suffix = getSuffix(files, imgName);

        while (suffix == null) {
            System.out.print("您输入的文件名有误，请重新输入【输入quit退出程序】：");
            imgName = scanner.nextLine();
            if ("quit".equals(imgName)) {
                return null;
            }
            suffix = getSuffix(files, imgName);
        }

        File f = new File(path + imgName + "." + suffix);
        return f;
    }
}
