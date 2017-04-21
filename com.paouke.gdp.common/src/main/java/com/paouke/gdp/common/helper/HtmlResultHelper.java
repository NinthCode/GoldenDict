package com.paouke.gdp.common.helper;

import com.paouke.gdp.common.constant.GdpCommonConstant;
import com.paouke.gdp.common.utils.StringUtils;

/**
 * Created by nicot on 17-4-19.
 */
public class HtmlResultHelper {
    public static String easyResultHtmlCreater(String engine, String src, String dst) {
        return GdpCommonConstant.EASY_HTMP_TEMPLATE.replaceAll(GdpCommonConstant.REGEX_TEMP_P_ENGINE, StringUtils.tropeSlashAnddollar(engine)).
                replaceAll(GdpCommonConstant.REGEX_TEMP_P_SRC, StringUtils.tropeSlashAnddollar(src)).
                replaceAll(GdpCommonConstant.REGEX_TEMP_P_DST, StringUtils.tropeSlashAnddollar(dst));
    }
}
