package com.paouke.gdp.common.utils;

import java.security.MessageDigest;

public class SHAUtil {
	private static final String encryModelSha1 = "SHA-1";
	/**
     * sha1.
     * 小写sha1加密
     * @param str
     * @return
     */
    public static String SHA1(String str) {
        return encrypt(encryModelSha1, str);
    }
    public static String encrypt(String algorithm, String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "";
        }
    }
    public static void main(String args[]){
        System.out.println(SHA1("hawkeye_r01hawkeye_s01qazxsw2"));
    }
}
