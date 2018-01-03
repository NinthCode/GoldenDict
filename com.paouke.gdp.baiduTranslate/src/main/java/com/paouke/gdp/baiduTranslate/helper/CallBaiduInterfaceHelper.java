package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.baiduTranslate.bean.DictResultBean;
import com.paouke.gdp.baiduTranslate.bean.LangDetectBean;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.common.utils.MD5Util;
import com.paouke.gdp.common.utils.RandomUtil;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.net.URLEncoder;

/**
 * Created by nicot on 17-4-18.
 */
public class CallBaiduInterfaceHelper {
    public static LangDetectBean callLangDetectInterface(WordsInfoBean wordsInfoBean) {
        LangDetectBean langDetectBean = new LangDetectBean();
        try {
            HttpResponse httpResponse = HttpRequest.post(GdpBaiduTranslateConstant.URL_BAIDU_TRANSLATE_LANGDETECT)
                    .header(HttpResponse.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .form("query", wordsInfoBean.getWordsAbstract())
                    .send();
            if (httpResponse.statusCode() != 200) {
                throw new Exception("错误的HTTP请求状态码！");
            }
            JSONObject respJO = JSON.parseObject(httpResponse.bodyText());
            langDetectBean.setError(respJO.getInteger(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_ERROR));
            langDetectBean.setMsg(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_MSG));
            if (langDetectBean.getError() == 0) {
                langDetectBean.setLangType(GdpBaiduTranslateConstant.LangType.valueOf(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_LAN)));
            }
        } catch (Exception e) {
            langDetectBean.setMsg(e.getMessage());
            langDetectBean.setError(-1);
        }
        return langDetectBean;
    }

    public static DictResultBean callV2transapiInterface(WordsInfoBean wordsInfoBean) {
        DictResultBean dictResultBean = new DictResultBean();
        try {
            Long salt = RandomUtil.random(100000000, 999999999);
            HttpResponse httpResponse = HttpRequest.post(GdpBaiduTranslateConstant.URL_BAIDU_TRANSLATE_V2TRANSAPI)
                    .header(HttpResponse.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .form("from", wordsInfoBean.getSourceLangType().toString())
                    .form("to", wordsInfoBean.getPurposeLangType().toString())
                    .form("q", wordsInfoBean.getWords())
                    .form("appid", GdpBaiduTranslateConstant.CONFIG.get().getAppId())
                    .form("salt", salt)
                    .form("sign", MD5Util.md5(GdpBaiduTranslateConstant.CONFIG.get().getAppId() + wordsInfoBean.getWords() + salt +GdpBaiduTranslateConstant.CONFIG.get().getAppKey()))
                    .formEncoding("UTF-8")
                    .send();
            if (httpResponse.statusCode() != 200) {
                throw new Exception("错误的HTTP请求状态码！code：" + httpResponse.statusCode());
            }
            JSONObject respJO = JSON.parseObject(httpResponse.bodyText());
            if(respJO.get(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_ERROR_CODE) == null) {
                dictResultBean.setFrom(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_FROM));
                dictResultBean.setTo(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_TO));
                JSONArray transResultJA = respJO.getJSONArray(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_TRANS_RESULT);
                if(transResultJA == null || transResultJA.isEmpty()) {
                    dictResultBean.setSrc( wordsInfoBean.getWords());
                    dictResultBean.setDst("暂无翻译！");
                } else {
                    dictResultBean.setSrc(transResultJA.getJSONObject(0).getString(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_SRC));
                    dictResultBean.setDst(transResultJA.getJSONObject(0).getString(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_DST));
                }
            } else {
                dictResultBean.setErrorCode(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_TRANSAPI_DICT_RESULT_ERROR_CODE));
            }
        } catch (Exception e) {
            dictResultBean.setErrorCode("调用接口失败" + e.getMessage());
        }
        return dictResultBean;
    }
}
