package com.paouke.gdp.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static byte[] readFileByBytes(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() <= 0) {
                return null;
            }
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String readFileByChars(String fileName, String charsetName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), charsetName == null ? "UTF-8" : charsetName);
            int tempChar;
            StringBuilder sb = new StringBuilder();
            while ((tempChar = reader.read()) != -1) {
                sb.append((char)tempChar);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            List<String> list = new ArrayList<>();
            String lineTemp = "";
            while ((lineTemp = bufferedReader.readLine()) != null) {
                list.add(lineTemp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeFile(String fileName, String content, boolean isAppend) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, isAppend);
            fileWriter.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        System.out.println(readFileByChars("recourse/html/help.html", null));
    }
}
