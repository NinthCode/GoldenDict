package com.paouke.gdp.youdaoTranslate.bean;

public class ConfigBean {
    //有道词典AppKey
    private String appKey;

    //有道词典密钥
    private String secretKey;

    //是否简单模式
    private boolean easy;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isEasy() {
        return easy;
    }

    public void setEasy(boolean easy) {
        this.easy = easy;
    }
}
