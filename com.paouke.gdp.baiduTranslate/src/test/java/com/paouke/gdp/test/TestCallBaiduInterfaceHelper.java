package com.paouke.gdp.test;


import com.alibaba.fastjson.JSON;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.baiduTranslate.helper.CallBaiduInterfaceHelper;
import org.junit.Test;

/**
 * Created by nicot on 17-4-18.
 */
public class TestCallBaiduInterfaceHelper {

    @Test
    public void testFunc_CallLangDetectInterface() {
        CallBaiduInterfaceHelper callBaiduInterfaceHelper = new CallBaiduInterfaceHelper();
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        wordsInfoBean.setWordsAbstract("Hello+words");
        System.out.println(JSON.toJSONString(callBaiduInterfaceHelper.callLangDetectInterface(wordsInfoBean)));
    }

    @Test
    public void testFunc_CallV2transapiInterface() {
        CallBaiduInterfaceHelper callBaiduInterfaceHelper = new CallBaiduInterfaceHelper();
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        wordsInfoBean.setWords("hello world");
        wordsInfoBean.setPurposeLangType(GdpBaiduTranslateConstant.LangType.zh);
        wordsInfoBean.setSourceLangType(GdpBaiduTranslateConstant.LangType.en);
        System.out.println(JSON.toJSONString(callBaiduInterfaceHelper.callV2transapiInterface(wordsInfoBean)));
    }
}
