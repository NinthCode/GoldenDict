package com.paouke.gdp.common.constant;

/**
 * Created by nicot on 17-4-19.
 */
public class GdpCommonConstant {
    public static final String EASY_HTMP_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\"/>\n" +
            "<style>\n" +
            "h3 {\n" +
            "\tpadding: 0.5ex;\n" +
            "}\n" +
            "div.fanyi_name {\n" +
            "\tbackground-color: #f9f9f9;\n" +
            "}\n" +
            "p.translation {\n" +
            "\tmargin: 0.5em;\n" +
            "\tpadding: 0.5em;\n" +
            "\tfont-size: 120%;\n" +
            "\ttext-indent: -0.5em;\n" +
            "}\n" +
            "span.water {\n" +
            "\tfloat:right;\n" +
            "\tfont-size: 140%;\n" +
            "\tfont-family: Sans-Serif;\n" +
            "\tcolor:#b9b9b9;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<span class=\"water\">#[engine]#</span>\n" +
            "<div class=\"fanyi_name\">\n" +
            "<h3>#[src]#</h3>\n" +
            "<hr/>\n" +
            "<p class=\"translation\">#[dst]#</p>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n";

    public static final String REGEX_TEMP_P_ENGINE = "\\#\\[engine\\]\\#";
    public static final String REGEX_TEMP_P_SRC = "\\#\\[src\\]\\#";
    public static final String REGEX_TEMP_P_DST = "\\#\\[dst\\]\\#";
}
