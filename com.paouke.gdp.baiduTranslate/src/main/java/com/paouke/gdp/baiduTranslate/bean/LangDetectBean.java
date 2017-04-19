package com.paouke.gdp.baiduTranslate.bean;

import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;

/**
 * Created by nicot on 17-4-18.
 */
public class LangDetectBean {
    private GdpBaiduTranslateConstant.LangType langType;

    private int error;

    private String msg;

    public GdpBaiduTranslateConstant.LangType getLangType() {
        return langType;
    }

    public void setLangType(GdpBaiduTranslateConstant.LangType langType) {
        this.langType = langType;
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
}
