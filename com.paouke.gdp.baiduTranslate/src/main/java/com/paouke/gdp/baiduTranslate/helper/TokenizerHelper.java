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
        if (wordArray.length > 3) {
            for (int i = 0; i < wordArray.length && i < 6; i++) {
                sb.append(wordArray[i]).append("+");
            }
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(words);
            if (sb.length() > 20) {
                sb.delete(20, sb.length());
            }
        }
        wordsInfoBean.setWordsAbstract(sb.toString());
    }

    public static void main(String[] args) {
        WordsInfoBean wordsInfoBean = new WordsInfoBean();
        TokenizerHelper tokenizerHelper = new TokenizerHelper();
        wordsInfoBean.setWords("[en->zh]:hello world");
        tokenizerHelper.extractOper(wordsInfoBean);
        System.out.println(JSON.toJSONString(wordsInfoBean));
    }
}
