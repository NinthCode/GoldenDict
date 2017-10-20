package com.paouke.gdp.common.constant;

/**
 * Created by nicot on 17-4-19.
 */
public class GdpCommonConstant {
    public static final String EASY_HTML_TEMPLATE = "<!DOCTYPE html>\n" +
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

    public static final String COMPLEX_HTML_TEMPLATE = "<!DOCTYPE html><html><head><meta charset=\"utf-8\"><style>h3{padding:.5ex;font-style:oblique;color:#CC7832;font-size:120%}div.fanyi_name{background-color:#f9f9f9}p.translation{margin:.5em;padding:.5em;font-size:120%;text-indent:-.5em}p.phonetic{font-size:90%;color:#8CBCE1}p.webmean{font-size:80%;color:gray}b.wordType{font-size:90%;font-style:oblique;color:#EAB1FF;text-indent:.5em}b.webmean-key{color:#24561c;text-indent:.5em}span.water{float:right;font-size:140%;font-family:Sans-Serif;color:#b9b9b9}strong.dict-comment-webmean{font-size:100%;color:#6A8759;text-indent:2em}strong.dict-comment-mean{color:#FFAE6D;text-indent:2em}div.webmean{margin-top:-.5em}</style></head><body><span class=\"water\">#[engine]#</span><div class=\"fanyi_name\"><h3>#[src]#</h3><hr>#[phonetic]#<div>#[mean]#</div>#[webmean-title]#<div class=\"webmean\">#[webmean]#</div></div></body></html>";

    public static final String COMPLEX_HTML_PHONETIC_TEMPLATE = "<p class=\"phonetic\">#[phonetic]#</p>";

    public static final String COMPLEX_HTML_MEAN_TEMPLATE = "        <p><b class=\"wordType\">#[key]#</b>\n"
            + "            <strong class=\"dict-comment-mean\">\n" + "                <span>#[value]#</span>\n"
            + "            </strong>\n" + "        </p>";

    public static final String COMPLEX_HTML_WEBMEAN_TEMPLATE = "        <p><b class=\"webmean-key\">#[key]#</b>\n"
            + "            <strong class=\"dict-comment-webmean\">\n" + "                <span>#[value]#</span>\n"
            + "            </strong>\n" + "        </p>";

    public static final String COMPLEX_HTML_WEBMEAN_TITLE_TEMPLATE = "<p class=\"webmean\">网络释义</p>";

    public static final String REGEX_TEMP_P_ENGINE = "#\\[engine\\]#";
    public static final String REGEX_TEMP_P_SRC = "#\\[src\\]#";
    public static final String REGEX_TEMP_P_DST = "#\\[dst\\]#";
    public static final String REGEX_TEMP_P_PHONETIC = "#\\[phonetic\\]#";
    public static final String REGEX_TEMP_P_KEY = "#\\[key\\]#";
    public static final String REGEX_TEMP_P_VALUE = "#\\[value\\]#";
    public static final String REGEX_TEMP_P_MEAN = "#\\[mean\\]#";
    public static final String REGEX_TEMP_P_WEBMEAN = "#\\[webmean\\]#";
    public static final String REGEX_TEMP_P_WEBMEAN_T = "#\\[webmean-title\\]#";



}
