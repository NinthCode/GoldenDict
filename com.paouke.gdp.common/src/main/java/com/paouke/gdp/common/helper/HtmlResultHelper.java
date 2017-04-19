package com.paouke.gdp.common.helper;

import com.paouke.gdp.common.constant.GdpCommonConstant;

/**
 * Created by nicot on 17-4-19.
 */
public class HtmlResultHelper {
    public static String easyResultHtmlCreater(String engine, String src, String dst) {
        return GdpCommonConstant.EASY_HTMP_TEMPLATE.replaceAll(GdpCommonConstant.REGEX_TEMP_P_ENGINE, engine).
                replaceAll(GdpCommonConstant.REGEX_TEMP_P_SRC, src).
                replaceAll(GdpCommonConstant.REGEX_TEMP_P_DST, dst);
    }
}
