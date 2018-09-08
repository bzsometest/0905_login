package com.bzchao.utils.etc;

import java.util.Base64;

/**
 * Base64测试
 */
public class Base64Test {
    public static void main(String[] args) {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        String str = encoder.encodeToString("chen网".getBytes());
        System.out.println(str);
        String ste = new String(decoder.decode(str));
        System.out.println(ste);
    }
}



