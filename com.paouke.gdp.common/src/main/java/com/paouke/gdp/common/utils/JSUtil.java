package com.paouke.gdp.common.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicot on 17-6-19.
 */
public class JSUtil {

    public static boolean runJs(String jsSrc, Map<String, Object> params, Map<String, Object> resMap) {
        if (StringUtils.isBlank(jsSrc)) {
            return false;
        }
        ScriptEngine scriptEngine = scriptEngineFactory();
        if (scriptEngine == null) {
            throw new RuntimeException("当前JAVA版本不支持JS执行引擎!");
        }
        injectValueMap(scriptEngine, params);
        try {
            scriptEngine.eval(jsSrc);
        } catch (Exception e) {
            loadResMap(scriptEngine, resMap);
            throw new RuntimeException(e);
        }
        loadResMap(scriptEngine, resMap);
        return true;
    }

    private static int getJavaBigVerion() {
        return Integer.parseInt(System.getProperty("java.version").split("\\.")[1]);
    }

    private static void injectValueMap(ScriptEngine scriptEngine, Map<String, Object> params) {
        if(params == null) {
            return;
        }
        for (String key : params.keySet()) {
            scriptEngine.put(key, params.get(key));
        }
    }

    private static void loadResMap(ScriptEngine scriptEngine, Map<String, Object> resMap) {
        if(resMap == null) {
            return;
        }
        for (String key : resMap.keySet()) {
            resMap.put(key, scriptEngine.get(key));
        }
    }

    private static ScriptEngine scriptEngineFactory() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        switch (getJavaBigVerion()) {
            case 6:
                return scriptEngineManager.getEngineByName("js");
            case 7:
                return scriptEngineManager.getEngineByName("javascript");
            case 8:
                return scriptEngineManager.getEngineByName("nashorn");
            case 9:
                return scriptEngineManager.getEngineByName("nashorn");
            default:
                throw new RuntimeException("不支持的JAVA版本!");
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>(){
            {
                this.put("TKK", null);
            }
        };
        System.out.println(runJs("var TKK=eval('((function(){var a\\x3d309588257;var b\\x3d2786275025;return 418994+\\x27.\\x27+(a+b)})())')", null, map));
        System.out.println(map.toString());
    }
}
