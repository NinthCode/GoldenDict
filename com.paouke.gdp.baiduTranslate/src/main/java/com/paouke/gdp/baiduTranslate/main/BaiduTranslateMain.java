package com.paouke.gdp.baiduTranslate.main;

import com.paouke.gdp.baiduTranslate.bean.ConfigBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateHtmlConstant;
import com.paouke.gdp.baiduTranslate.helper.ConfigHelper;
import com.paouke.gdp.baiduTranslate.service.BaiduTranslateService;
import com.paouke.gdp.common.utils.FileUtil;
import org.junit.Test;

/**
 * Created by nicot on 17-4-14.
 */
public class BaiduTranslateMain {
    public static void main(String[] args){
        ConfigBean configBean = null;
        if(args.length >= 2) {
            configBean = ConfigHelper.loadConfig(args[1]);
        } else {
            if(ConfigHelper.loadJarPathConfig()) {
                configBean = GdpBaiduTranslateConstant.CONFIG.get();
            }
        }
        if(configBean == null) {
            ConfigHelper.createNewConfig();
            System.out.println("查询失败: 配置文件不存在！已在Jar所在文件夹下创建，请进行配置！");
            return ;
        }
        if(args.length >= 1) {
            if("?".equals(args[0])) {
                System.out.println(GdpBaiduTranslateHtmlConstant.HTML_HELP_DOC);
            } else {
                System.out.println(new BaiduTranslateService().doTranslate(args[0]));
            }
        } else {
            System.out.println("查询失败: 无翻译词！");
        }
    }
    @Test
    public void testFunc_DoTranslate() {
        System.out.println(new BaiduTranslateService().doTranslate("Microsoft hello"));
    }
}
