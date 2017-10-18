package com.paouke.gdp.youdaoTranslate.helper;

import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.common.utils.MD5Util;
import com.paouke.gdp.common.utils.RandomUtil;
import com.paouke.gdp.youdaoTranslate.bean.ConfigBean;
import com.paouke.gdp.youdaoTranslate.bean.DictResultBean;
import com.paouke.gdp.youdaoTranslate.bean.WordsInfoBean;
import com.paouke.gdp.youdaoTranslate.constant.GdpYoudaoTranslateConstant;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class CallYoudaoApiHelper {

    public static DictResultBean CallTranslateApi(WordsInfoBean wordsInfoBean, ConfigBean configBean) {
        String salt = RandomUtil.getRandomCode(10);
        HttpResponse httpResponse = HttpRequest.get(GdpYoudaoTranslateConstant.URL_YOUDAO_TRANSLATE)
                .header("Content-Type","text/html; charset=utf-8")
                .query("q", wordsInfoBean.getWords())
                .query("from", "auto")
                .query("to", "auto")
                .query("appKey", configBean.getAppKey())
                .query("salt", salt)
                .query("sign", MD5Util.md5(configBean.getAppKey() + wordsInfoBean.getWords() + salt + configBean.getSecretKey()))
                .send();
        if(httpResponse.statusCode() == 200) {
            return JSONObject.toJavaObject(JSONObject.parseObject(httpResponse.bodyText()), DictResultBean.class);
        } else {
            DictResultBean dictResultBean = new DictResultBean();
            dictResultBean.setErrorCode("-1");
            return dictResultBean;
        }
    }
}
