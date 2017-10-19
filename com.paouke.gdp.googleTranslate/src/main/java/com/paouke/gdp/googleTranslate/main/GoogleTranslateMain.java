package com.paouke.gdp.googleTranslate.main;

import com.paouke.gdp.googleTranslate.bean.ConfigBean;
import com.paouke.gdp.googleTranslate.constant.GdpGoogleTranslateConstant;
import com.paouke.gdp.googleTranslate.helper.ConfigHelper;
import com.paouke.gdp.googleTranslate.service.GoogleTranslateService;
import org.junit.Test;

/**
 * Created by nicot on 17-10-18.
 */
public class GoogleTranslateMain {
    public static void main(String[] args){
        if(! ((args.length >= 2) ? ConfigHelper.loadConfig(args[1]) : ConfigHelper.loadJarPathConfig())) {
            ConfigHelper.createNewConfig();
            System.out.println("查询失败: 配置文件不存在！已在Jar所在文件夹下创建，请进行配置！");
            return ;
        }
        if(args.length >= 1) {
            System.out.println(new GoogleTranslateService().doTranslate(args[0]));
        } else {
            System.out.println("查询失败: 无翻译词！");
        }
    }

    @Test
    public void test_translate() {
    }
}
