package com.paouke.gdp.youdaoTranslate.constant;

import java.util.HashMap;

public class GdpYoudaoTranslateConstant {

    //有道翻译HTTP地址
    public static final String URL_YOUDAO_TRANSLATE = "http://openapi.youdao.com/api";

    //默认的配置文件名称
    public static final String NAME_DEFALUT_CONFIG = "YDTConfig.json";

    //有道词典返回正常Code
    public static final String KEY_YOUDAO_CODE_NORMAL = "0";

    public static final HashMap<String, String> ERROR_CODE_MAP = new HashMap<String, String> (){
        {
            this.put("101", "缺少必填的参数，出现这个情况还可能是et的值和实际加密方式不对应");
            this.put("102", "不支持的语言类型");
            this.put("103", "翻译文本过长");
            this.put("104", "不支持的API类型");
            this.put("105", "不支持的签名类型");
            this.put("106", "不支持的响应类型");
            this.put("107", "不支持的传输加密类型");
            this.put("108", "appKey无效，注册账号， 登录后台创建应用和实例并完成绑定， 可获得应用ID和密钥等信息，其中应用ID就是appKey（ 注意不是应用密钥）");
            this.put("109", "batchLog格式不正确");
            this.put("110", "无相关服务的有效实例");
            this.put("111", "开发者账号无效，可能是账号为欠费状态");
            this.put("201", "解密失败，可能为DES,BASE64,URLDecode的错误");
            this.put("202", "签名检验失败");
            this.put("203", "访问IP地址不在可访问IP列表");
            this.put("301", "辞典查询失败");
            this.put("302", "翻译查询失败");
            this.put("303", "服务端的其它异常");
            this.put("401", "账户已经欠费停");
            this.put("-1", "请求返回非200");
        }
    };

    public static final String ENGINE_NAME = "有道翻译";

}
