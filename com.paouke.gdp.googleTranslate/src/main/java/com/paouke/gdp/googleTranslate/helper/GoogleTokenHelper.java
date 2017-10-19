package com.paouke.gdp.googleTranslate.helper;

import com.paouke.gdp.common.utils.JSUtil;
import com.paouke.gdp.googleTranslate.bean.WordsInfoBean;
import com.paouke.gdp.googleTranslate.constant.GdpGoogleTranslateConstant;

import java.util.HashMap;
import java.util.Map;

public class GoogleTokenHelper {
    public static String calcToken(WordsInfoBean wordsInfoBean) {
        if(GdpGoogleTranslateConstant.CONFIG.get().getTkk() == null && !loadTkk()) {
            throw new RuntimeException("计算TKK失败");
        }
        Map<String, Object> runJsParams = new HashMap<>();
        Map<String, Object> runJsRes = new HashMap<>();
        runJsParams.put("words", wordsInfoBean.getWords());
        runJsParams.put("TokenKey", GdpGoogleTranslateConstant.CONFIG.get().getTkk());
        runJsRes.put("token", null);

        if(! JSUtil.runJs(GdpGoogleTranslateConstant.JS_TOKEN_CALC_ALGORITHM, runJsParams, runJsRes)) {
            throw new RuntimeException("计算Token失败");
        } else {
            return (String)runJsRes.get("token");
        }
    }

    public static boolean loadTkk() {
        try {
            String TkkAlgorithm = CallGoogleApiHelper.getGoogleTkkAlgorithm();
            Map<String, Object> runJsResMap = new HashMap<>();
            runJsResMap.put("TKK", null);
            if(JSUtil.runJs(TkkAlgorithm, null , runJsResMap)) {
                GdpGoogleTranslateConstant.CONFIG.get().setTkk((String)runJsResMap.get("TKK"));
                ConfigHelper.updateConfig();
                return true;
            } else {
                System.out.println("计算TKK失败，请检查TKK算法：" + TkkAlgorithm);
                return false;
            }
        } catch (Exception e) {
            System.out.println("加载TKK算法失败: " + e.getMessage());
            return false;
        }
    }
}
