package com.paouke.gdp.baiduTranslate.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by nicot on 17-6-2.
 */
public class DictResultBean {
    //词典来源
    private String from;

    //单词名称
    private String wordName;

    //单词意思
    private List<String> wordMeans;

    //单词难度
    private List<String> cores;

    //单词标签
    private List<String> tags;

    //单词其他类型
    private Map<String, List<String>> exchange;

    //音标
    private Map<String, String> phoneticSymbol;

    //音标读音
    private Map<String, String> phoneticSymbolTTS;

    //TTS
    private Map<String, String> TTS;

    //示意
    private Map<String, List<String>> parts;

    //柯林斯词典
    private JSONObject collins;

    //错误
    private String errorMsg;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public List<String> getWordMeans() {
        return wordMeans;
    }

    public void setWordMeans(List<String> wordMeans) {
        this.wordMeans = wordMeans;
    }

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Map<String, List<String>> getExchange() {
        return exchange;
    }

    public void setExchange(Map<String, List<String>> exchange) {
        this.exchange = exchange;
    }

    public Map<String, String> getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(Map<String, String> phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public Map<String, String> getPhoneticSymbolTTS() {
        return phoneticSymbolTTS;
    }

    public void setPhoneticSymbolTTS(Map<String, String> phoneticSymbolTTS) {
        this.phoneticSymbolTTS = phoneticSymbolTTS;
    }

    public Map<String, String> getTTS() {
        return TTS;
    }

    public void setTTS(Map<String, String> TTS) {
        this.TTS = TTS;
    }

    public Map<String, List<String>> getParts() {
        return parts;
    }

    public void setParts(Map<String, List<String>> parts) {
        this.parts = parts;
    }

    public JSONObject getCollins() {
        return collins;
    }

    public void setCollins(JSONObject collins) {
        this.collins = collins;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
