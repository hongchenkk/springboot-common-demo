package com.godink.springboot.common.demo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件操作代码
 * 
 */
public class FileUtils {
    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }
    
    public static void writeToFile(String sqlStr, String filePath) throws IOException {
        OutputStream out = new FileOutputStream(filePath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(sqlStr);
        writer.close();
        out.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtils.readToBuffer(sb, filePath);
        return sb.toString();
    }
    
    public static List<File> readFilesByDir(String dirPath) {
    	File dir = new File(dirPath);
    	if(dir.exists() && dir.isDirectory()) {
    		File[] fileArr = dir.listFiles();
    		return Arrays.asList(fileArr);
    	}
    	return new ArrayList<>();
    }
    
    public static void main(String[] args) throws Exception{
    	//filterFile();
//    	delOldFile();
//    	getFileNamePrefixDot();
//    	generateFieldAddSql();
    	
    	generateSqlField();
	}
    
    public static void generateSqlField() throws IOException {
    	String filePath1 = "D:\\tmp\\testtxt\\api1.txt";
    	String filePath2 = "D:\\tmp\\testtxt\\api2.txt";
    	String readFile = FileUtils.readFile(filePath1);
    	String[] split = readFile.split("_");
    	String newStr = "";
    	for (int i = 0; i < split.length; i++) {
			if(i > 0) {
				String first = split[i].substring(0,1).toUpperCase();
				String after = split[i].substring(1).toLowerCase();
				newStr += first + after;
			}else {
				newStr += split[i];
			}
		}
    	//将所有_x开头的x都变成大写的并去掉_
    	FileUtils.writeToFile(newStr, filePath2);
    }
    
}
