package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.baiduTranslate.bean.LangDetectBean;
import com.paouke.gdp.baiduTranslate.bean.V2transapiBean;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

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

    public static V2transapiBean callV2transapiInterface(WordsInfoBean wordsInfoBean) {
        V2transapiBean v2transapiBean = new V2transapiBean();
        try {
            HttpResponse httpResponse = HttpRequest.post(GdpBaiduTranslateConstant.URL_BAIDU_TRANSLATE_V2TRANSAPI)
                    .header(HttpResponse.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .form("from", wordsInfoBean.getSourceLangType().toString())
                    .form("to", wordsInfoBean.getPurposeLangType().toString())
                    .form("query", wordsInfoBean.getWords())
                    .form("transtype", "realtime")
                    .form("simple_means_flag", "3")
                    .send();
            if (httpResponse.statusCode() != 200) {
                throw new Exception("错误的HTTP请求状态码！");
            }
            JSONObject respJO = JSON.parseObject(httpResponse.bodyText());
            if(respJO.get(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_ERROR) == null) {
                v2transapiBean.setError(0);
                JSONObject transResultJO = respJO.getJSONObject(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_TRANS_RESULT);
                JSONArray data = transResultJO.getJSONArray(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DATA);
                if(data.size() > 0) {
                    JSONObject dataJO = data.getJSONObject(0);
                    v2transapiBean.setDestination(dataJO.getString(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DST));
                    v2transapiBean.setSource(dataJO.getString(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_SRC));
                }
                v2transapiBean.setPhonetic(transResultJO.getJSONArray(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_PHONETIC));
                v2transapiBean.setKeywords(transResultJO.getJSONArray(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_KEYWORDS));
                v2transapiBean.setDictResult(respJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT));
                v2transapiBean.setLijuResult(respJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_LIJU_RESULT));
            } else {
                v2transapiBean.setError(respJO.getInteger(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_ERROR));
                v2transapiBean.setMsg(respJO.getString(GdpBaiduTranslateConstant.JSON_KEY_LANG_DETECT_MSG));
            }
        } catch (Exception e) {
            v2transapiBean.setMsg(e.getMessage());
            v2transapiBean.setError(-1);
        }
        return v2transapiBean;
    }
}
