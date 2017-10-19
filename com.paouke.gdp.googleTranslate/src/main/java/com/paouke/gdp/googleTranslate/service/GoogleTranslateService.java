package com.paouke.gdp.googleTranslate.service;

import com.paouke.gdp.common.helper.HtmlResultHelper;
import com.paouke.gdp.googleTranslate.bean.ConfigBean;
import com.paouke.gdp.googleTranslate.bean.DictResultBean;
import com.paouke.gdp.googleTranslate.bean.WordsInfoBean;
import com.paouke.gdp.googleTranslate.constant.GdpGoogleTranslateConstant;
import com.paouke.gdp.googleTranslate.helper.CallGoogleApiHelper;
import com.paouke.gdp.googleTranslate.helper.GoogleTokenHelper;

/**
 * Created by nicot on 17-10-18.
 */
public class GoogleTranslateService {

    public String doTranslate(String words) {
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        wordsInfoBean.setWords(words);
        try {
            wordsInfoBean.setToken(GoogleTokenHelper.calcToken(wordsInfoBean));
            DictResultBean dictResultBean = CallGoogleApiHelper.callTranslateApi(wordsInfoBean);
            if(dictResultBean == null) {
                GoogleTokenHelper.loadTkk();
                dictResultBean = CallGoogleApiHelper.callTranslateApi(wordsInfoBean);
            }
            if(dictResultBean == null) {
                return "查询失败: Token计算错误";
            }
            return HtmlResultHelper.easyResultHtmlCreater(GdpGoogleTranslateConstant.ENGINE_NAME, dictResultBean.getQuery(), dictResultBean.getTranslation());
        } catch (Exception e) {
            return "查询失败: " + e.getMessage();
        }
    }
}
