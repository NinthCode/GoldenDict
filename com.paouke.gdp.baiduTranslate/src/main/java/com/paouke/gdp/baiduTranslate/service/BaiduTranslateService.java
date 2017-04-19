package com.paouke.gdp.baiduTranslate.service;

import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.baiduTranslate.bean.LangDetectBean;
import com.paouke.gdp.baiduTranslate.bean.V2transapiBean;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.baiduTranslate.helper.CallBaiduInterfaceHelper;
import com.paouke.gdp.baiduTranslate.helper.TokenizerHelper;
import com.paouke.gdp.common.constant.GdpCommonConstant;
import com.paouke.gdp.common.helper.HtmlResultHelper;

/**
 * Created by nicot on 17-4-14.
 */
public class BaiduTranslateService {

    public String doTranslate(String words) {
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        TokenizerHelper tokenizerHelper = new TokenizerHelper();
        wordsInfoBean.setWords(words);
        TokenizerHelper.extractOper(wordsInfoBean);
        TokenizerHelper.extractAbst(wordsInfoBean);
        if(!wordsInfoBean.isForceLangType()) {
            LangDetectBean langDetectBean = CallBaiduInterfaceHelper.callLangDetectInterface(wordsInfoBean);
            if(langDetectBean.getError() == 0){
                wordsInfoBean.setSourceLangType(langDetectBean.getLangType());
                if(langDetectBean.getLangType().equals(GdpBaiduTranslateConstant.LangType.zh)) {
                    wordsInfoBean.setPurposeLangType(GdpBaiduTranslateConstant.LangType.en);
                } else {
                    wordsInfoBean.setPurposeLangType(GdpBaiduTranslateConstant.LangType.zh);
                }
            } else {
                return "";
            }
        }
        V2transapiBean v2transapiBean = CallBaiduInterfaceHelper.callV2transapiInterface(wordsInfoBean);
        String result = HtmlResultHelper.easyResultHtmlCreater(GdpBaiduTranslateConstant.ENGINE_NAME, v2transapiBean.getSource(), v2transapiBean.getDestination());
        return v2transapiBean.getError() == 0 ? result : v2transapiBean.getMsg();
    }
}
