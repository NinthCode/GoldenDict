package com.paouke.gdp.common.helper;

import com.paouke.gdp.common.bean.DictShowBean;
import com.paouke.gdp.common.constant.GdpCommonConstant;
import com.paouke.gdp.common.utils.StringUtils;

import java.util.Map;

/**
 * Created by nicot on 17-4-19.
 */
public class HtmlResultHelper {
    public static String easyResultHtmlCreater(String engine, String src, String dst) {
        return GdpCommonConstant.EASY_HTML_TEMPLATE
                .replaceAll(GdpCommonConstant.REGEX_TEMP_P_ENGINE, StringUtils.tropeSlashAnddollar(engine)).
                        replaceAll(GdpCommonConstant.REGEX_TEMP_P_SRC, StringUtils.tropeSlashAnddollar(src)).
                        replaceAll(GdpCommonConstant.REGEX_TEMP_P_DST, StringUtils.tropeSlashAnddollar(dst));
    }

    public static String complexResultHtmlCreater(String engine, DictShowBean dictShowBean) {
        if (!dictShowBean.isDetail()) {
            return easyResultHtmlCreater(engine, dictShowBean.getWords(), dictShowBean.getTranslation());
        }
        //替换开始
        String result = GdpCommonConstant.COMPLEX_HTML_TEMPLATE
                .replaceAll(GdpCommonConstant.REGEX_TEMP_P_ENGINE, StringUtils.tropeSlashAnddollar(engine))
                .replaceAll(GdpCommonConstant.REGEX_TEMP_P_SRC, StringUtils.tropeSlashAnddollar(dictShowBean.getWords()))
                .replaceAll(GdpCommonConstant.REGEX_TEMP_P_PHONETIC, StringUtils.tropeSlashAnddollar(buildPhonetic(dictShowBean.getUkPhonetic(), dictShowBean.getUsPhonetic())))
                .replaceAll(GdpCommonConstant.REGEX_TEMP_P_MEAN, StringUtils.tropeSlashAnddollar(buildMeans(dictShowBean.getExplains())));
        if(dictShowBean.getWebMeans() == null || dictShowBean.getWebMeans().size() != 0) {
            result = result.replaceAll(GdpCommonConstant.REGEX_TEMP_P_WEBMEAN_T, StringUtils.tropeSlashAnddollar(GdpCommonConstant.COMPLEX_HTML_WEBMEAN_TITLE_TEMPLATE))
                           .replaceAll(GdpCommonConstant.REGEX_TEMP_P_WEBMEAN, StringUtils.tropeSlashAnddollar(buildWebMeans(dictShowBean.getWebMeans())));
        }
        return result;
    }

    private static String buildPhonetic(String uk, String us) {
        return GdpCommonConstant.COMPLEX_HTML_PHONETIC_TEMPLATE.replaceAll(GdpCommonConstant.REGEX_TEMP_P_PHONETIC, StringUtils.tropeSlashAnddollar("英:[" + uk + "],美:[" + us + "]"));
    }

    private static String buildMeans(Map<String, String> explains) {
        if(explains == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(String key : explains.keySet()) {
            sb.append(GdpCommonConstant.COMPLEX_HTML_MEAN_TEMPLATE.replaceAll(GdpCommonConstant.REGEX_TEMP_P_KEY, StringUtils.tropeSlashAnddollar(key))
                    .replaceAll(GdpCommonConstant.REGEX_TEMP_P_VALUE, explains.get(key)));
        }
        return sb.toString();
    }

    private static String buildWebMeans(Map<String, String> webMeans) {
        if(webMeans == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(String key : webMeans.keySet()) {
            sb.append(GdpCommonConstant.COMPLEX_HTML_WEBMEAN_TEMPLATE.replaceAll(GdpCommonConstant.REGEX_TEMP_P_KEY, StringUtils.tropeSlashAnddollar(key))
                    .replaceAll(GdpCommonConstant.REGEX_TEMP_P_VALUE, StringUtils.tropeSlashAnddollar(webMeans.get(key))));
        }
        return sb.toString();
    }
}
