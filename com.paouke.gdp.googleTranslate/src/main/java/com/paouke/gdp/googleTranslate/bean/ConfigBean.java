package com.paouke.gdp.googleTranslate.bean;

public class ConfigBean {

    //是否简单模式
    private boolean easy;

    //TKK
    private String Tkk;

    //开发者模式
    private boolean devMode = false;

    public boolean isEasy() {
        return easy;
    }

    public void setEasy(boolean easy) {
        this.easy = easy;
    }

    public String getTkk() {
        return Tkk;
    }

    public void setTkk(String tkk) {
        Tkk = tkk;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }
}
