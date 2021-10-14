package com.godink.springboot.common.demo.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 文件转base64编码及反转
 * @ClassName: FileToBase64 
 * @author: Hong.Chen
 * @date: 2021年10月14日 上午10:05:04
 */
public class FileToBase64 {
    
    public static void main(String[] args) {
        try {
            String base64Code =encodeBase64File("D:/test.doc");
            decoderBase64File(base64Code, "D:/test223.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * <p>将文件转成base64 字符串</p>
    * @param path 文件路径
    * @return
    * @throws Exception
    */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
    /**
    * <p>将base64字符解码保存文件</p>
    * @param base64Code
    * @param targetPath
    * @throws Exception
    */
    public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
   
}