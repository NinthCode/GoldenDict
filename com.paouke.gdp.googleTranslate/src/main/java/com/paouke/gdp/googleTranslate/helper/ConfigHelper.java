package com.paouke.gdp.googleTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.paouke.gdp.common.utils.FileUtil;
import com.paouke.gdp.common.utils.JarUtil;
import com.paouke.gdp.googleTranslate.bean.ConfigBean;
import com.paouke.gdp.googleTranslate.constant.GdpGoogleTranslateConstant;

public class ConfigHelper {
    public static boolean loadJarPathConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpGoogleTranslateConstant.NAME_DEFALUT_CONFIG;
        return loadConfig(configFilePath);
    }

    public static boolean loadConfig(String path) {
        String configStr = FileUtil.readFileByChars(path, "UTF-8");
        if(configStr == null) return false;
        GdpGoogleTranslateConstant.CONFIG.set(JSONObject.toJavaObject(JSON.parseObject(configStr), ConfigBean.class));
        return true;
    }

    public static void createNewConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpGoogleTranslateConstant.NAME_DEFALUT_CONFIG;
        ConfigBean configBean = new ConfigBean();
        FileUtil.writeFile(configFilePath, JSON.toJSONString(configBean, SerializerFeature.WriteMapNullValue), false);
    }

    public static void updateConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpGoogleTranslateConstant.NAME_DEFALUT_CONFIG;
        FileUtil.writeFile(configFilePath, JSON.toJSONString(GdpGoogleTranslateConstant.CONFIG.get(), SerializerFeature.WriteMapNullValue), false);
    }
}
