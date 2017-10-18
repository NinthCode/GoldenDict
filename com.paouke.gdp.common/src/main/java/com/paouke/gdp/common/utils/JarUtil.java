package com.paouke.gdp.common.utils;

import java.io.File;

public class JarUtil {

    //获取jar绝对路径
    public static String getJarPath()
    {
        File file = getFile();
        if (file == null)
            return null;
        return file.getAbsolutePath();
    }

    //获取jar目录
    public static String getJarDir()
    {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getParent();
    }

    //获取jar包名
    public static String getJarName()
    {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getName();
    }

    //获取当前Jar文件
    private static File getFile()
    {
        // 关键是这行...
        String path = JarUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try
        {
            path = java.net.URLDecoder.decode(path, "UTF-8"); // 转换处理中文及空格
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            return null;
        }
        return new File(path);
    }
}
