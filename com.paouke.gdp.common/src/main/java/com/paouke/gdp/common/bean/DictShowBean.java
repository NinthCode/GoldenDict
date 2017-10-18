package com.paouke.gdp.common.bean;

import java.util.Map;

public class DictShowBean {

    //源语言
    private String words;

    //是否显示详细意思
    private boolean detail;

    //翻译结果
    private String translation;

    //英国音标
    private String ukPhonetic;

    //美国音标
    private String usPhonetic;

    //词典释义
    private Map<String, String> explains;

    //网络释义
    private Map<String, String> webMeans;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public Map<String, String> getExplains() {
        return explains;
    }

    public void setExplains(Map<String, String> explains) {
        this.explains = explains;
    }

    public Map<String, String> getWebMeans() {
        return webMeans;
    }

    public void setWebMeans(Map<String, String> webMeans) {
        this.webMeans = webMeans;
    }
}
