package com.paouke.gdp.baiduTranslate.bean;

import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;

/**
 * Created by nicot on 17-4-17.
 */
public class WordsInfoBean {
    //需要翻译的段落的摘要，以加号分割，用于区分语言
    private String wordsAbstract;

    //源语言类型
    private GdpBaiduTranslateConstant.LangType sourceLangType;

    //目的语言类型
    private GdpBaiduTranslateConstant.LangType purposeLangType;

    //正文
    private String words;

    //是否强制翻译
    private boolean isForceLangType;

    public String getWordsAbstract() {
        return wordsAbstract;
    }

    public void setWordsAbstract(String wordsAbstract) {
        this.wordsAbstract = wordsAbstract;
    }

    public GdpBaiduTranslateConstant.LangType getSourceLangType() {
        return sourceLangType;
    }

    public void setSourceLangType(GdpBaiduTranslateConstant.LangType sourceLangType) {
        this.sourceLangType = sourceLangType;
    }

    public GdpBaiduTranslateConstant.LangType getPurposeLangType() {
        return purposeLangType;
    }

    public void setPurposeLangType(GdpBaiduTranslateConstant.LangType purposeLangType) {
        this.purposeLangType = purposeLangType;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setIsForceLangType(boolean isForceLangType) {
        this.isForceLangType = isForceLangType;
    }


    public boolean isForceLangType() {
        return isForceLangType;
    }

    public void setForceLangType(boolean forceLangType) {
        isForceLangType = forceLangType;
    }
}
