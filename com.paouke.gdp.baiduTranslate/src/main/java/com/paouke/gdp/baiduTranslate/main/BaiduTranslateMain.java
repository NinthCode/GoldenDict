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
        System.out.println(new BaiduTranslateService().doTranslate("一个自动化的代码 分析工具，可以帮助 开发者船/质量 更好的软件，更 快。与codacy，你得到的静态分析，圈复杂度和代码，复制在每一把拉请求单元测试覆盖率的变化"));
    }
}
