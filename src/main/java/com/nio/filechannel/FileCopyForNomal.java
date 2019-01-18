package com.nio.filechannel;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * 普通的文件复制
 */
public class FileCopyForNomal {
    public void fileCopy(File fromFile,File toFile){
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new BufferedInputStream(new FileInputStream(fromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(toFile,true));//追加
            byte [] bytes = new byte[1024];
            int i;
            while((i=inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }if(outputStream!=null){
                    outputStream.close();
                }
            }catch (IOException e){
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
        new FileCopyForNomal().fileCopy(fromFile,toFile);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
