package com.paouke.gdp.baiduTranslate.main;

import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateHtmlConstant;
import com.paouke.gdp.baiduTranslate.service.BaiduTranslateService;
import com.paouke.gdp.common.utils.FileUtil;
import org.junit.Test;

/**
 * Created by nicot on 17-4-14.
 */
public class BaiduTranslateMain {
    public static void main(String[] args){
        if(args.length >= 1) {
            if("?".equals(args[0])) {
                System.out.println(GdpBaiduTranslateHtmlConstant.HTML_HELP_DOC);
            } else {
                System.out.println(new BaiduTranslateService().doTranslate(args[0]));
            }
        } else {
            System.out.println("null");
        }
    }
    @Test
    public void testFunc_DoTranslate() {
        System.out.println(new BaiduTranslateService().doTranslate("Microsoft hello"));
    }
}
