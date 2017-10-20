package com.paouke.gdp.youdaoTranslate.main;

import com.alibaba.fastjson.JSON;
import com.paouke.gdp.youdaoTranslate.bean.ConfigBean;
import com.paouke.gdp.youdaoTranslate.helper.ConfigHelper;
import com.paouke.gdp.youdaoTranslate.service.YoudaoTranslateService;
import org.junit.Test;

/**
 * Created by nicot on 17-10-18.
 */
public class YoudaoTranslateMain {
    public static void main(String[] args){
        ConfigBean configBean = null;
        if(args.length >= 2) {
            configBean = ConfigHelper.loadConfig(args[1]);
        } else {
            configBean = ConfigHelper.loadJarPathConfig();
        }
        if(configBean == null) {
            ConfigHelper.createNewConfig();
            System.out.println("查询失败: 配置文件不存在！已在Jar所在文件夹下创建，请进行配置！");
            return ;
        }
        if(args.length >= 1) {
            System.out.println(new YoudaoTranslateService().doTranslate(args[0], configBean));
        } else {
            System.out.println("查询失败: 无翻译词！");
        }
    }

    @Test
    public void test_translate() {
        ConfigBean configBean = new ConfigBean();
        configBean.setAppKey("");
        configBean.setSecretKey("");
        configBean.setEasy(false);
        System.out.println(new YoudaoTranslateService().doTranslate("CamelCase", configBean));
    }
}
