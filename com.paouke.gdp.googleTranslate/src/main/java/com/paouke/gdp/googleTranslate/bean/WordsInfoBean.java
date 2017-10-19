package com.paouke.gdp.googleTranslate.bean;

/**
 * Created by nicot on 17-4-17.
 */
public class WordsInfoBean {
    //正文
    private String words;

    //token
    private String token;

    //源语言类型
    private String sourceLangType;

    //目的语言类型
    private String purposeLangType;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSourceLangType() {
        return sourceLangType;
    }

    public void setSourceLangType(String sourceLangType) {
        this.sourceLangType = sourceLangType;
    }

    public String getPurposeLangType() {
        return purposeLangType;
    }

    public void setPurposeLangType(String purposeLangType) {
        this.purposeLangType = purposeLangType;
    }
}
