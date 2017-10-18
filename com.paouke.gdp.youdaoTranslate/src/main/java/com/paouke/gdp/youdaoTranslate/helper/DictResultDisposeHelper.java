package com.paouke.gdp.youdaoTranslate.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.common.bean.DictShowBean;
import com.paouke.gdp.youdaoTranslate.bean.DictResultBean;
import com.paouke.gdp.youdaoTranslate.constant.GdpYoudaoTranslateConstant;

import java.util.HashMap;
import java.util.Map;

public class DictResultDisposeHelper {

    public static DictShowBean disposeResultToDictShowBean(DictResultBean dictResultBean) {
        DictShowBean dictShowBean = new DictShowBean();
        dictShowBean.setWords(dictResultBean.getQuery());
        dictShowBean.setDetail(dictResultBean.getBasic().size() != 0);
        dictShowBean.setTranslation(dictResultBean.getTranslation().getString(0));
        dictShowBean.setUkPhonetic(dictResultBean.getBasic().getString(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_UK_PHONETIC));
        dictShowBean.setUsPhonetic(dictResultBean.getBasic().getString(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_US_PHONETIC));
        Map<String, String> explains = new HashMap<>();
        Map<String, String> webMeans = new HashMap<>();
        JSONArray JAexplains = dictResultBean.getBasic().getJSONArray(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_EXPLAINS);
        if(JAexplains != null) {
            for(Object obj : JAexplains) {
                String explain = obj.toString();
                String[] temp = explain.split("\\.");
                explains.put(temp[0], explain.replace(temp[0], ""));
            }
        }
        dictShowBean.setExplains(explains);
        JSONArray JAwebMean = dictResultBean.getWeb();
        if(JAwebMean != null) {
            for(int i = 0;i < JAwebMean.size();i++) {
                JSONObject temp = JAwebMean.getJSONObject(i);
                String key = temp.getString(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_KEY);
                JSONArray JAvalue = temp.getJSONArray(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_VALUE);
                StringBuilder valueSb = new StringBuilder();
                for(Object obj : JAvalue) {
                    valueSb.append(obj.toString()).append(";");
                }
                webMeans.put(key, valueSb.toString());
            }
        }
        dictShowBean.setWebMeans(webMeans);
        return dictShowBean;
    }
}
