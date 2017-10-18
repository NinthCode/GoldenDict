package com.paouke.gdp.youdaoTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.paouke.gdp.common.utils.FileUtil;
import com.paouke.gdp.common.utils.JarUtil;
import com.paouke.gdp.youdaoTranslate.bean.ConfigBean;
import com.paouke.gdp.youdaoTranslate.constant.GdpYoudaoTranslateConstant;

public class ConfigHelper {
    public static ConfigBean loadJarPathConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpYoudaoTranslateConstant.NAME_DEFALUT_CONFIG;
        return loadConfig(configFilePath);
    }

    public static ConfigBean loadConfig(String path) {
        String configStr = FileUtil.readFileByChars(path, "UTF-8");
        if(configStr == null) return null;
        return JSONObject.toJavaObject(JSON.parseObject(configStr), ConfigBean.class);
    }

    public static void createNewConfig() {
        String JarPath = JarUtil.getJarDir();
        String configFilePath = JarPath + "/" + GdpYoudaoTranslateConstant.NAME_DEFALUT_CONFIG;
        ConfigBean configBean = new ConfigBean();
        FileUtil.writeFile(configFilePath, JSON.toJSONString(configBean, SerializerFeature.WriteMapNullValue), false);
    }
}
