package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.baiduTranslate.bean.LangDetectBean;
import com.paouke.gdp.baiduTranslate.bean.V2transapiBean;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;
import com.paouke.gdp.common.utils.HttpClientUtil;
import java.net.URLEncoder;

/**
 * Created by nicot on 17-4-18.
 */
public class CallBaiduInterfaceHelper {
    public static LangDetectBean callLangDetectInterface(WordsInfoBean wordsInfoBean) {
        LangDetectBean langDetectBean = new LangDetectBean();
        try {
            String body = "query=" + URLEncoder.encode(wordsInfoBean.getWordsAbstract(), "UTF-8");
            String resp = HttpClientUtil.httpClientPost(GdpBaiduTranslateConstant.URL_BAIDU_TRANSLATE_LANGDETECT, body, null, "application/x-www-form-urlencoded");
            if ("-1".equals(resp)) {
                throw new Exception("错误的HTTP请求状态码！");
            }
            JSONObject respJO = JSON.parseObject(resp);
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
            StringBuffer sb = new StringBuffer();
            sb.append("from=").append(wordsInfoBean.getSourceLangType()).append("&");
            sb.append("to=").append(wordsInfoBean.getPurposeLangType()).append("&");
            sb.append("query=").append(URLEncoder.encode(wordsInfoBean.getWords(), "UTF-8")).append("&");
            sb.append("transtype=").append("realtime").append("&");
            sb.append("simple_means_flag=").append("3");
            String resp = HttpClientUtil.httpClientPost(GdpBaiduTranslateConstant.URL_BAIDU_TRANSLATE_V2TRANSAPI, sb.toString(), null, "application/x-www-form-urlencoded");
            if ("-1".equals(resp)) {
                throw new Exception("错误的HTTP请求状态码！");
            }
            JSONObject respJO = JSON.parseObject(resp);
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
