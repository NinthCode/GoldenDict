package com.paouke.gdp.googleTranslate.bean;

public class DictResultBean {
    //源语言
    private String query;

    //翻译结果
    private String translation;

    //源语言类型
    private String sourceLangType;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getSourceLangType() {
        return sourceLangType;
    }

    public void setSourceLangType(String sourceLangType) {
        this.sourceLangType = sourceLangType;
    }
}
