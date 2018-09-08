package com.bzchao.utils.etc;

import java.security.*;
import java.security.interfaces.*;
import java.math.*;
import java.util.Arrays;

/**
 * RSA测试
 */
public class RSADemo {
    public RSADemo() {
    }

    PublicKey pbkey;
    PrivateKey prkey;

    public void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            pbkey = kp.getPublic();
            prkey = kp.getPrivate();
        } catch (Exception e) {
        }
    }

    //加密，需要公钥
    public byte[] encrypt(byte[] ptext) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey pbk = (RSAPublicKey) pbkey;
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    //使用私钥进行解密
    public byte[] decrypt(byte[] ctext) throws Exception {
        // 读取密文
        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        RSAPrivateKey prk = (RSAPrivateKey) prkey;
        BigInteger d = prk.getPrivateExponent();

        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        BigInteger m = c.modPow(d, n);

        // 显示解密结果
        byte[] mt = m.toByteArray();
        return mt;
    }

    public PublicKey getPbkey() {
        return pbkey;
    }

    public void setPbkey(PublicKey pbkey) {
        this.pbkey = pbkey;
    }

    public PrivateKey getPrkey() {
        return prkey;
    }

    public void setPrkey(PrivateKey prkey) {
        this.prkey = prkey;
    }

    public static void main(String[] args) throws Exception {
        RSADemo rsaDemo = new RSADemo();
        rsaDemo.generateKey();
        System.out.println(Arrays.toString(rsaDemo.getPbkey().getEncoded()));

        System.out.println(Arrays.toString(rsaDemo.getPrkey().getEncoded()));
        String mi = new String(rsaDemo.getPrkey().getEncoded());
        System.out.println(mi);

        PrivateKey privateKey = new PrivateKey() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return mi.getBytes();
            }
        };
        byte[] bytes = rsaDemo.encrypt("chen".getBytes());
        RSADemo r2 = new RSADemo();
        byte[] bytesDe = rsaDemo.decrypt(bytes);
        System.out.println(new String(bytesDe));
    }
}
