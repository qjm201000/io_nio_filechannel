package com.nio.filechannel;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 通道文件复制
 */
public class FileCopyForChannel {
    public void fileCopy(File fromFile, File toFile){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileOutputStream中
            fileChannelInput.transferTo(0,fileChannelInput.size(),fileChannelOutput);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String from = ResourceUtils.getFile("classpath:a.txt").getPath();//这边文件是在target下
        System.out.println(from);
        String to = ResourceUtils.getFile("classpath:b.txt").getPath();//这边文件是在target下
        System.out.println(to);
        File fromFile = new File(from);
        File toFile = new File(to);

        Long startTime = System.currentTimeMillis();
        new FileCopyForChannel().fileCopy(fromFile,toFile);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
