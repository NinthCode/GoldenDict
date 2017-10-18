package com.paouke.gdp.youdaoTranslate.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DictResultBean {

    //错误返回码
    private String errorCode;

    //源语言
    private String query;

    //翻译结果
    private JSONArray translation;

    //词义,词典
    private JSONObject basic;

    //词义，网络释义
    private JSONArray web;

    //源语言和目标语言
    private String language;

    //词典deeplink
    private JSONObject dict;

    //webdeeplink
    private JSONObject webdict;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public JSONArray getTranslation() {
        return translation;
    }

    public void setTranslation(JSONArray translation) {
        this.translation = translation;
    }

    public JSONObject getBasic() {
        return basic;
    }

    public void setBasic(JSONObject basic) {
        this.basic = basic;
    }

    public JSONArray getWeb() {
        return web;
    }

    public void setWeb(JSONArray web) {
        this.web = web;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public JSONObject getDict() {
        return dict;
    }

    public void setDict(JSONObject dict) {
        this.dict = dict;
    }

    public JSONObject getWebdict() {
        return webdict;
    }

    public void setWebdict(JSONObject webdict) {
        this.webdict = webdict;
    }
}
