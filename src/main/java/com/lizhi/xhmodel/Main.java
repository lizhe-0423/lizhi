package com.lizhi.xhmodel;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * @name lizhi
 * @description
 * @data 2023 2023/10/23 20:26
 */
public class Main {
    public static void main(String[] args) {

        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        pair.getPrivate(); // 私钥写入当前user表 公钥是共有的写不写入都可以 加密信息传递给sdk
        PublicKey publicKey = pair.getPublic();// 传递公钥给sdk
        System.out.println(pair.getPublic()); // 公钥就一个 持久化到本地 每次请求都会发送此公钥
        RSA rsa = new RSA(pair.getPrivate(),publicKey);
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是user id=13,我要像url:12发送请求", CharsetUtil.CHARSET_UTF_8)
                , KeyType.PrivateKey);
        // sdk 私钥加密 公钥解密 接收加密信息 和公钥

        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey); //公钥进行解密 向解密后url发送请求
        System.out.println(StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
    }
}