package com.paouke.gdp.baike.main;


import com.paouke.gdp.baike.constant.GdpBaikeConstant;
import com.paouke.gdp.common.utils.HttpClientUtil;

import java.net.URLEncoder;

/**
 * Created by nicot on 17-4-14.
 */
public class BaikeMain {
    public static void main(String[] args) throws Exception{
        if(args.length > 0) {
            System.out.println(HttpClientUtil.httpClientGet(GdpBaikeConstant.URL_BAIDU_BAIKE + URLEncoder.encode(args[0], "UTF-8")));
        } else {
            System.out.println("NULL");
        }
    }
}
