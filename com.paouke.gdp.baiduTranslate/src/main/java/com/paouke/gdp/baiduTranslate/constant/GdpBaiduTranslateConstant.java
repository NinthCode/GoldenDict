package com.paouke.gdp.baiduTranslate.constant;

/**
 * Created by nicot on 17-4-14.
 */
public class GdpBaiduTranslateConstant {

    //获取语言类型API
    public static final String URL_BAIDU_TRANSLATE_LANGDETECT = "http://fanyi.baidu.com/langdetect";

    //获取翻译API
    public static final String URL_BAIDU_TRANSLATE_V2TRANSAPI = "http://fanyi.baidu.com/v2transapi";

    //语言类型
    public enum LangType {
        en, zh, jp, kor, wyw, ru, de, yue, cht
    }

    //操作符分割符
    public static final String OPERATION_DELIMITER = ":";
    //是否为操作符匹配正则
    public static final String OPERATION_REGEX = "\\[((en)|(zh)|(jp)|(kor)|(wyw)|(ru)|(de)|(yue)|(cht)|(\\?))->((en)|(zh)|(jp)|(kor)|(wyw)|(ru)|(de)|(yue)|(cht)|(\\?))\\]";

    public static final String JSON_KEY_LANG_DETECT_ERROR = "error";
    public static final String JSON_KEY_LANG_DETECT_MSG = "msg";
    public static final String JSON_KEY_LANG_DETECT_LAN = "lan";
    public static final String JSON_KEY_V2TRANSAPI_TRANS_RESULT = "trans_result";
    public static final String JSON_KEY_V2TRANSAPI_DATA = "data";
    public static final String JSON_KEY_V2TRANSAPI_DST = "dst";
    public static final String JSON_KEY_V2TRANSAPI_SRC = "src";
    public static final String JSON_KEY_V2TRANSAPI_PHONETIC = "phonetic";
    public static final String JSON_KEY_V2TRANSAPI_KEYWORDS = "keywords";
    public static final String JSON_KEY_V2TRANSAPI_DICT_RESULT = "dict_result";
    public static final String JSON_KEY_V2TRANSAPI_LIJU_RESULT = "liju_result";

    public static final String ENGINE_NAME = "百度翻译";

    //语言类型识别取样长度阈值
    public static final int CONF_RUN_LANG_DETECT_LENGTH = 40;
    //语言类型识别分词类型辞数阈值
    public static final int CONF_RUN_LANG_DETECT_APPRECIATION_WORD_NUM = 3;
}
