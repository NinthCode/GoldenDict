package com.paouke.gdp.googleTranslate.constant;

import com.paouke.gdp.googleTranslate.bean.ConfigBean;

public class GdpGoogleTranslateConstant {

    //爬虫爬取地址
    public static final String URL_GOOGLE_CRAWLER = "https://translate.google.cn/";

    //谷歌翻译HTTP地址
    public static final String URL_GOOGLE_TRANSLATE = "http://translate.google.cn/translate_a/single?client=t&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&pc=1&otf=1&ssel=6&tsel=3&kc=0";

    //默认的配置文件名称
    public static final String NAME_DEFALUT_CONFIG = "GTConfig.json";

    public static final String ENGINE_NAME = "谷歌翻译";

    public static final String KEY_LANGUAGE_TYPE_EN = "en";

    public static final String KEY_LANGUAGE_TYPE_ZNCH = "zh-CN";

    public static final String KEY_LANGUAGE_TYPE_AUTO = "auto";

    public static final String JS_TOKEN_CALC_ALGORITHM = "var b=function(a,b){for(var d=0;d<b.length-2;d+=3){var c=b.charAt(d+2),c=\"a\"<=c?c.charCodeAt(0)-87:Number(c),c=\"+\"==b.charAt(d+1)?a>>>c:a<<c;a=\"+\"==b.charAt(d)?a+c&4294967295:a^c}return a};var tk=function(a,TKK){for(var e=TKK.split(\".\"),h=Number(e[0])||0,g=[],d=0,f=0;f<a.length;f++){var c=a.charCodeAt(f);128>c?g[d++]=c:(2048>c?g[d++]=c>>6|192:(55296==(c&64512)&&f+1<a.length&&56320==(a.charCodeAt(f+1)&64512)?(c=65536+((c&1023)<<10)+(a.charCodeAt(++f)&1023),g[d++]=c>>18|240,g[d++]=c>>12&63|128):g[d++]=c>>12|224,g[d++]=c>>6&63|128),g[d++]=c&63|128)}a=h;for(d=0;d<g.length;d++)a+=g[d],a=b(a,\"+-a^+6\");a=b(a,\"+-3^+b+-f\");a^=Number(e[1])||0;0>a&&(a=(a&2147483647)+2147483648);a%=1E6;return a.toString()+\".\"+(a^h)};var token=tk(words,TokenKey);";

    public static final ThreadLocal<ConfigBean> CONFIG = new ThreadLocal<>();
}
