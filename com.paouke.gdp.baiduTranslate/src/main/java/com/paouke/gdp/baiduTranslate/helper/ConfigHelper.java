package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.paouke.gdp.baiduTranslate.bean.ConfigBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.common.utils.FileUtil;
import com.paouke.gdp.common.utils.JarUtil;

public class ConfigHelper {
    public static boolean loadJarPathConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpBaiduTranslateConstant.NAME_DEFALUT_CONFIG;
        ConfigBean configBean = loadConfig(configFilePath);
        if(configBean == null) {
            return false;
        }
        GdpBaiduTranslateConstant.CONFIG.set(configBean);
        return true;
    }

    public static ConfigBean loadConfig(String path) {
        String configStr = FileUtil.readFileByChars(path, "UTF-8");
        if(configStr == null) return null;
        return JSONObject.toJavaObject(JSON.parseObject(configStr), ConfigBean.class);
    }

    public static void createNewConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpBaiduTranslateConstant.NAME_DEFALUT_CONFIG;
        ConfigBean configBean = new ConfigBean();
        FileUtil.writeFile(configFilePath, JSON.toJSONString(configBean, SerializerFeature.WriteMapNullValue), false);
    }
}
