package com.wuan.weekly.util;


import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;


public class MD5Utils {
    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String generate(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String password, String md5) {
        if (generate(password).equals(md5)) {
            return true;
        } else {
            return false;
        }
    }

}