package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.baiduTranslate.bean.DictResultBean;
import com.paouke.gdp.baiduTranslate.bean.V2transapiBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicot on 17-6-2.
 */
public class DictResultDisposeHelper {
    public DictResultBean disposeDictResult(V2transapiBean v2transapiBean) {
        DictResultBean dictResultBean = new DictResultBean();
        Object dictResultObj = v2transapiBean.getDictResult();
        if(dictResultObj == null || !(dictResultObj instanceof Map)) {
            return dictResultBean;
        }
        try{
            JSONObject dictResultJO = (JSONObject) dictResultObj;
            Object simpleMeansObj = dictResultJO.get(
                    GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_SIMPLE_MEANS);
            if(simpleMeansObj == null || !(simpleMeansObj instanceof Map)) {
                return dictResultBean;
            }
            JSONObject simpleMeansJO = (JSONObject) simpleMeansObj;
            dictResultBean.setFrom(simpleMeansJO.getString(
                    GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_FROM));
            dictResultBean.setWordName(simpleMeansJO.getString(
                    GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_WORD_NAME));
            disposeWordMeans(dictResultBean, simpleMeansJO);
            disposeTags(dictResultBean, simpleMeansJO);
            disposeExchange(dictResultBean, simpleMeansJO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void disposeWordMeans(DictResultBean dictResultBean, JSONObject simpleMeansJO) {
        List<String> wordMeans = new ArrayList<>();
        dictResultBean.setWordMeans(wordMeans);
        Object wordMeansObj = simpleMeansJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_WORD_MEANS);
        if(wordMeansObj == null || !(wordMeansObj instanceof List)) {
            return ;
        }
        JSONArray wordMeanArray = (JSONArray)wordMeansObj;
        for(Object obj : wordMeanArray) {
            wordMeans.add(obj.toString());
        }
    }

    private void disposeTags(DictResultBean dictResultBean, JSONObject simpleMeansJO) {
        List<String> cores = new ArrayList<>();
        List<String> others = new ArrayList<>();
        dictResultBean.setCores(cores);
        dictResultBean.setTags(others);
        Object tagsObj = simpleMeansJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_TAGS);
        if(tagsObj == null || !(tagsObj instanceof Map)) {
            return ;
        }
        JSONObject tagsJO = (JSONObject) tagsObj;
        Object coresObj = tagsJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_CORE);
        if(coresObj == null || !(coresObj instanceof List)) {
            return ;
        }
        JSONArray coresJO = (JSONArray) coresObj;
        for(Object obj : coresJO) {
            cores.add(obj.toString());
        }

        Object othersObj = tagsJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_OTHER);
        if(othersObj == null || !(othersObj instanceof List)) {
            return ;
        }
        JSONArray othersJO = (JSONArray) othersObj;
        for(Object obj : othersJO) {
            others.add(obj.toString());
        }
    }

    private void disposeExchange(DictResultBean dictResultBean, JSONObject simpleMeansJO) {
        Map<String, List<String>> exchangeMap = new HashMap<>();
        dictResultBean.setExchange(exchangeMap);
        Object exchangeObj = simpleMeansJO.get(GdpBaiduTranslateConstant.JSON_KEY_V2TRANSAPI_DICT_RESULT_EXCHANGE);
        if(exchangeObj == null || !(exchangeObj instanceof Map)) {
            return;
        }
        JSONObject exchangeJO = (JSONObject) exchangeObj;
        for(Object key : exchangeJO.keySet()) {
            List<String> tempList = new ArrayList<>();
            Object value = exchangeJO.get(key.toString());
            if(value != null && value instanceof List) {
                JSONArray valueArray = (JSONArray) value;
                for(Object obj : valueArray) {
                    tempList.add(obj == null ? null : obj.toString());
                }
            } else {
                if(value != null ){
                    tempList.add(value.toString());
                }
            }
            exchangeMap.put(key.toString(), tempList);
        }
    }
}
