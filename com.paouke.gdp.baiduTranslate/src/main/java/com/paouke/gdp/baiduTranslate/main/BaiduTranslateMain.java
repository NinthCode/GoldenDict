package com.paouke.gdp.baiduTranslate.main;

import com.paouke.gdp.baiduTranslate.service.BaiduTranslateService;
import org.junit.Test;

/**
 * Created by nicot on 17-4-14.
 */
public class BaiduTranslateMain {
    public static void main(String[] args){
        if(args.length >= 1) {
            System.out.println(new BaiduTranslateService().doTranslate(args[0]));
        } else {
            System.out.println("null");
        }
    }
    @Test
    public void testFunc_DoTranslate() {
        System.out.println(new BaiduTranslateService().doTranslate("10086会回复你：尊敬的客户,您好!您正在点播由中国红十字会公司提供的捐款2业务,信息费2.0元/条,(不含通信费,由中国移动代收费"));
    }
}
