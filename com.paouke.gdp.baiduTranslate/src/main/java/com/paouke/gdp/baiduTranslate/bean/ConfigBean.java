package com.paouke.gdp.baiduTranslate.bean;

public class ConfigBean {

    //appid
    private String appId;

    //密钥
    private String appKey;

    //语言类型识别取样长度阈值
    private int langDetectLength = 40;

    private int langDetectAppreciationWordNum = 3;

    //开发者模式
    private boolean devMode = false;

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public int getLangDetectLength() {
        return langDetectLength;
    }

    public void setLangDetectLength(int langDetectLength) {
        this.langDetectLength = langDetectLength;
    }

    public int getLangDetectAppreciationWordNum() {
        return langDetectAppreciationWordNum;
    }

    public void setLangDetectAppreciationWordNum(int langDetectAppreciationWordNum) {
        this.langDetectAppreciationWordNum = langDetectAppreciationWordNum;
    }
}
