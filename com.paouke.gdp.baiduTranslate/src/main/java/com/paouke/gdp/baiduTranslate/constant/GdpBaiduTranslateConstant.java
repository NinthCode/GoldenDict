package com.paouke.gdp.baiduTranslate.constant;

import com.paouke.gdp.baiduTranslate.bean.ConfigBean;

/**
 * Created by nicot on 17-4-14.
 */
public class GdpBaiduTranslateConstant {

    //获取语言类型API
    public static final String URL_BAIDU_TRANSLATE_LANGDETECT = "http://fanyi.baidu.com/langdetect";

    //获取翻译API
    public static final String URL_BAIDU_TRANSLATE_V2TRANSAPI = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    //默认的配置文件名称
    public static final String NAME_DEFALUT_CONFIG = "BDTConfig.json";

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
    public static final String JSON_KEY_TRANSAPI_TRANS_RESULT = "trans_result";
    public static final String JSON_KEY_TRANSAPI_DICT_RESULT_DST = "dst";
    public static final String JSON_KEY_TRANSAPI_DICT_RESULT_SRC = "src";
    public static final String JSON_KEY_TRANSAPI_DICT_RESULT_FROM = "from";
    public static final String JSON_KEY_TRANSAPI_DICT_RESULT_TO = "to";
    public static final String JSON_KEY_TRANSAPI_DICT_RESULT_ERROR_CODE = "error_code";

    public static final String ENGINE_NAME = "百度翻译";

    public static final ThreadLocal<ConfigBean> CONFIG = new ThreadLocal<>();

}
