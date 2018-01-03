package com.paouke.gdp.baiduTranslate.helper;

import com.alibaba.fastjson.JSON;
import com.paouke.gdp.baiduTranslate.bean.WordsInfoBean;
import com.paouke.gdp.baiduTranslate.constant.GdpBaiduTranslateConstant;

import java.util.regex.Pattern;

/**
 * Created by nicot on 17-4-17.
 */
public class TokenizerHelper {
    public static void extractOper(WordsInfoBean wordsInfoBean) {
        String words = wordsInfoBean.getWords();
        String[] wordArray = words.split(GdpBaiduTranslateConstant.OPERATION_DELIMITER);
        String source = wordArray[0];
        if (Pattern.matches(GdpBaiduTranslateConstant.OPERATION_REGEX, source)) {
            source = source.replaceAll("[\\[\\]]", "");
            String[] langs = source.split("->");
            if (langs.length == 2) {
                try {
                    if (!"?".equals(langs[0])) {
                        wordsInfoBean.setSourceLangType(GdpBaiduTranslateConstant.LangType.valueOf(langs[0]));
                        wordsInfoBean.setIsForceLangType(true);
                    }
                    if (!"?".equals(langs[1])) {
                        wordsInfoBean.setPurposeLangType(GdpBaiduTranslateConstant.LangType.valueOf(langs[1]));
                        wordsInfoBean.setIsForceLangType(true);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            if (wordsInfoBean.isForceLangType()) {
                wordsInfoBean.setWords(wordArray[1]);
            }
        }
    }

    public static void extractAbst(WordsInfoBean wordsInfoBean) {
        String words = wordsInfoBean.getWords();
        String[] wordArray = words.split(" ");
        StringBuffer sb = new StringBuffer();
        if (wordArray.length > GdpBaiduTranslateConstant.CONFIG.get().getLangDetectAppreciationWordNum()) {
            for (int i = 0; i < wordArray.length; i++) {
                if(sb.length() + wordArray[i].length() + 1 > GdpBaiduTranslateConstant.CONFIG.get().getLangDetectLength()) {
                    break;
                }
                if(sb.length() != 0) {
                    sb.append("+");
                }
                sb.append(wordArray[i]);
            }
        }
        if(sb.length() == 0) {
            sb.append(words.toCharArray(), 0, words.length() < GdpBaiduTranslateConstant.CONFIG.get().getLangDetectLength() ?
                    words.length() : GdpBaiduTranslateConstant.CONFIG.get().getLangDetectLength());
        }
        wordsInfoBean.setWordsAbstract(sb.toString());
    }
}
