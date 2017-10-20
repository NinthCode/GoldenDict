package com.paouke.gdp.googleTranslate.helper;

import com.alibaba.fastjson.JSONArray;
import com.paouke.gdp.googleTranslate.bean.DictResultBean;
import com.paouke.gdp.googleTranslate.bean.WordsInfoBean;
import com.paouke.gdp.googleTranslate.constant.GdpGoogleTranslateConstant;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class CallGoogleApiHelper {

    public static String getGoogleTkkAlgorithm() {
        HttpResponse httpResponse = HttpRequest.get(GdpGoogleTranslateConstant.URL_GOOGLE_CRAWLER)
                .header("User-Agent", "Mozilla/5.0")
                .send();
        String html = httpResponse.bodyText();
        if(html.contains("TKK=")) {
            return "var TKK" + html.split("TKK")[1].split("\\);")[0] + ");";
        } else {
            return null;
        }
    }

    public static DictResultBean callTranslateApi(WordsInfoBean wordsInfoBean) {
        if(wordsInfoBean.getSourceLangType() == null) wordsInfoBean.setSourceLangType(GdpGoogleTranslateConstant.KEY_LANGUAGE_TYPE_AUTO);
        if(wordsInfoBean.getPurposeLangType() == null) wordsInfoBean.setPurposeLangType(GdpGoogleTranslateConstant.KEY_LANGUAGE_TYPE_ZNCH);
        DictResultBean dictResultBean = callGoogleTranslateApi(wordsInfoBean);
        if(dictResultBean == null) return null;
        if(GdpGoogleTranslateConstant.KEY_LANGUAGE_TYPE_ZNCH.equals(dictResultBean.getSourceLangType())) {
            wordsInfoBean.setPurposeLangType(GdpGoogleTranslateConstant.KEY_LANGUAGE_TYPE_EN);
            return callGoogleTranslateApi(wordsInfoBean);
        } else {
            return dictResultBean;
        }
    }

    private static DictResultBean callGoogleTranslateApi(WordsInfoBean wordsInfoBean) {
        DictResultBean dictResultBean = new DictResultBean();
        HttpResponse httpResponse = HttpRequest.get(GdpGoogleTranslateConstant.URL_GOOGLE_TRANSLATE)
                .header("User-Agent", "Mozilla/5.0")
                .query("sl", wordsInfoBean.getSourceLangType())
                .query("tl", wordsInfoBean.getPurposeLangType())
                .query("tk", wordsInfoBean.getToken())
                .query("q", wordsInfoBean.getWords())
                .send();
        if(httpResponse.statusCode() != 200) {
            return null;
        } else {
            JSONArray JARes = JSONArray.parseArray(httpResponse.bodyText());
            if(JARes != null && JARes.size() >= 3) {
                dictResultBean.setSourceLangType(JARes.getString(2));
                JSONArray JATrans = JARes.getJSONArray(0);
                if(JATrans != null) {
                    StringBuilder querySb = new StringBuilder();
                    StringBuilder tranSb = new StringBuilder();
                    for(int i = 0; i < JATrans.size() - 1; i++) {
                        JSONArray JATran = JATrans.getJSONArray(i);
                        querySb.append(JATran.getString(1));
                        tranSb.append(JATran.getString(0));
                    }
                    dictResultBean.setQuery(querySb.toString());
                    dictResultBean.setTranslation(tranSb.toString());
                }
            } else {
                throw new RuntimeException("处理错误！");
            }
        }
        return dictResultBean;
    }

    public static void main(String[] args){
        System.out.println(getGoogleTkkAlgorithm());
    }
}
