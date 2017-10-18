package com.paouke.gdp.youdaoTranslate.service;

import com.alibaba.fastjson.JSON;
import com.paouke.gdp.common.helper.HtmlResultHelper;
import com.paouke.gdp.youdaoTranslate.bean.ConfigBean;
import com.paouke.gdp.youdaoTranslate.bean.DictResultBean;
import com.paouke.gdp.youdaoTranslate.bean.WordsInfoBean;
import com.paouke.gdp.youdaoTranslate.constant.GdpYoudaoTranslateConstant;
import com.paouke.gdp.youdaoTranslate.helper.CallYoudaoApiHelper;
import com.paouke.gdp.youdaoTranslate.helper.DictResultDisposeHelper;

/**
 * Created by nicot on 17-10-18.
 */
public class YoudaoTranslateService {

    public String doTranslate(String words, ConfigBean configBean) {
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        wordsInfoBean.setWords(words);
        try {
            DictResultBean dictResultBean = CallYoudaoApiHelper.CallTranslateApi(wordsInfoBean, configBean);
            return GdpYoudaoTranslateConstant.KEY_YOUDAO_CODE_NORMAL.equals(dictResultBean.getErrorCode()) ?
                    (configBean.isEasy() ?
                            HtmlResultHelper.easyResultHtmlCreater(GdpYoudaoTranslateConstant.ENGINE_NAME, words,
                                    dictResultBean.getTranslation().getString(0)) :
                            HtmlResultHelper.complexResultHtmlCreater(GdpYoudaoTranslateConstant.ENGINE_NAME,
                                    DictResultDisposeHelper.disposeResultToDictShowBean(dictResultBean))) :
                    "查询失败: " + GdpYoudaoTranslateConstant.ERROR_CODE_MAP.get(dictResultBean.getErrorCode());
        } catch (Exception e) {
            return "查询失败: " + e.getMessage();
        }
    }
}
