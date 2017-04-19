package com.paouke.gdp.baiduTranslate.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by nicot on 17-4-18.
 */
public class V2transapiBean {


    //翻译之后的内容
    private String destination;
    //翻译之前的内容
    private String source;
    //错误信息
    private int error;
    //MSG
    private String msg;
    //注音
    private JSONArray phonetic;
    //关键字
    private JSONArray keywords;
    //词典结果
    private Object dictResult;
    //例句
    private Object lijuResult;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONArray getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(JSONArray phonetic) {
        this.phonetic = phonetic;
    }

    public JSONArray getKeywords() {
        return keywords;
    }

    public void setKeywords(JSONArray keywords) {
        this.keywords = keywords;
    }

    public Object getDictResult() {
        return dictResult;
    }

    public void setDictResult(Object dictResult) {
        this.dictResult = dictResult;
    }

    public Object getLijuResult() {
        return lijuResult;
    }

    public void setLijuResult(Object lijuResult) {
        this.lijuResult = lijuResult;
    }
}
