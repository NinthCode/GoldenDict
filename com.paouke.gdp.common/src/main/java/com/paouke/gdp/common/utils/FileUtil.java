package com.paouke.gdp.common.utils;

import java.io.*;

/**
 * 
 *  @Project       : com.creditease.pharos.common
 *  @Program Name  : com.creditease.pharos.common.util.FileUtil.java
 *  @Class Name    : FileUtil
 *  @Copyright	   : Copyright (c)2016-2015   
 *  @Company	   : CreditEase
 *  @Description   : 为了测试方便，写一个工具类，把关注的信息写在文件里
 *  @Author        : 刘静芳
 *  @Creation Date : 2016年10月27日 下午3:43:48 
 *  @ModificationHistory  
 *  Date			Author      	Version    	Description  
 *  ------------------------------------------------------------------  
 *  2016年10月27日		           刘静芳			1.0			1.0 Version
 */
public class FileUtil {
	/*运行成功返回值*/
	private static final boolean bSuccess = true;
	/*运行失败返回值*/
	private static final boolean bFail = false;
	private static final String sFail = "Failed";
	/**
	 * 
	 *  @Description    : 读文件
	 *  @Method_Name    : readFile
	 *  @param FilePath
	 *  @return 
	 *  @Creation Date  : 2016年10月27日 下午3:52:25 
	 *  @Author         : 刘静芳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年10月27日			刘静芳				1.0			创建
	 */
	public static String readFile(String FilePath) {
		try {
			/* 读入TXT文件 */  
			String pathname = FilePath;//"D:\\twitter\\13_9_6\\dataset\\en\\input.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
			File filename = new File(pathname); // 要读取以上路径的input。txt文件  
			InputStreamReader reader = new InputStreamReader(  
			        new FileInputStream(filename)); // 建立一个输入流对象reader  
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
			String line = "";  
			line = br.readLine();  
			while (line != null) {  
			    line = br.readLine(); // 一次读入一行数据  
			}
			return line;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return sFail;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return sFail;
		}  
	}
	/**
	 * 
	 *  @Description    : 写文件
	 *  @Method_Name    : writeFile
	 *  @param FilePath
	 *  @param content
	 *  @return 
	 *  @Creation Date  : 2016年10月27日 下午3:52:11 
	 *  @Author         : 刘静芳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年10月27日			刘静芳				1.0			创建
	 */
	public static boolean writeFile(String FilePath,String content) {
		 /* 写入Txt文件 */  
        try {
			File writename = new File(FilePath); // ".\\result\\en\\output.txt",相对路径，如果没有则要建立一个新的output。txt文件  
			writename.createNewFile(); // 创建新文件  
			BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));  
			out.write(content + "\r\n"); // \r\n即为换行  
			out.flush(); // 把缓存区内容压入文件  
			out.close(); // 最后记得关闭文件  
			return bSuccess;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bFail;
		}
	}

	public static boolean mkdirAndCreateFile(String filePath, String content) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					if(!file.getParentFile().mkdirs()) {
						return false;
					}
				}
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(content);
				fileWriter.flush();
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String readFileByChars(String fileName){
		File file = new File(fileName);
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			StringBuffer sb = new StringBuffer();
			while ((tempchar = reader.read()) != -1) {
				sb.append((char)tempchar);
			}
			reader.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
