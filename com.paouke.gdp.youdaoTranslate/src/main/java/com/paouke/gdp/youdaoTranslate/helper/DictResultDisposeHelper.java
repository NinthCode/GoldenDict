package com.paouke.gdp.youdaoTranslate.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paouke.gdp.common.bean.DictShowBean;
import com.paouke.gdp.youdaoTranslate.bean.DictResultBean;
import com.paouke.gdp.youdaoTranslate.bean.WordsInfoBean;
import com.paouke.gdp.youdaoTranslate.constant.GdpYoudaoTranslateConstant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class DictResultDisposeHelper {

    public static DictShowBean disposeResultToDictShowBean(WordsInfoBean wordsInfoBean, DictResultBean dictResultBean) {
        DictShowBean dictShowBean = new DictShowBean();
        Map<String, String> explains = new HashMap<>();
        Map<String, String> webMeans = new HashMap<>();
        dictShowBean.setWords(wordsInfoBean.getWords());
        dictShowBean.setDetail(dictResultBean.getBasic() != null && dictResultBean.getBasic().size() != 0);
        dictShowBean.setTranslation(dictResultBean.getTranslation().getString(0));
        if(dictResultBean.getBasic() != null) {
            dictShowBean.setUkPhonetic(dictResultBean.getBasic().getString(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_UK_PHONETIC));
            dictShowBean.setUsPhonetic(dictResultBean.getBasic().getString(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_US_PHONETIC));
            JSONArray JAexplains = dictResultBean.getBasic().getJSONArray(GdpYoudaoTranslateConstant.JSON_KEY_RESULT_BASIC_EXPLAINS);
            if(JAexplains != null) {
                for(Object obj : JAexplains) {
                    String[] temp = splitExplain(obj.toString());
                    if(temp[0] == null) {
                        explains.put(temp[1], "");
                    } else {
                        explains.put(temp[0], temp[1]);
                    }
                }
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

    /**
     * 拆分单词解释。例如：将 "vt. 分离; 使分离" 拆分为 ['vt.', '分离; 使分离']
     *
     * @return 一个长度为2的数组，数组第一个元素为单词词性，如果解释中不存在词性则为null。数组第二个元素为单词解释。
     */
    private static String[] splitExplain(String input) {
        String[] result = new String[2];
        Matcher explainMatcher = GdpYoudaoTranslateConstant.PATTERN_EXPLAIN.matcher(input);
        if (explainMatcher.find()) {
            result[0] = explainMatcher.group(GdpYoudaoTranslateConstant.GROUP_CLASS);
            result[1] = explainMatcher.group(GdpYoudaoTranslateConstant.GROUP_EXPLAIN).trim();
        } else {
            result[1] = input;
        }

        return result;
    }
}
