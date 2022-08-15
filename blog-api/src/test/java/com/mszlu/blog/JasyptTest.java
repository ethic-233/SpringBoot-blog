//package com.mszlu.blog;
//
//import org.jasypt.encryption.StringEncryptor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class JasyptTest {
//
//    //加密
//    @Test
//    public  void getPass(){
//        String name = new StringEncryptor().encrypt("root");
//        String password = encryptor.encrypt("root");
//        System.out.println(name); //hifVDwDPW5MNYa0tAabvJQ==
//        System.out.println(password);  //5mTXmB5IYPxqiSuD/045cjHEkJz2NhNi
//    }
//    //解密
//    @Test
//    public  void passDecrypt(){
//        String username = encryptor.decrypt("GBUgBremWPmZyOBduCGpjg==");
//        String password = encryptor.decrypt("vTVcTpYIklCxL09ntaYwSZuvVXGGh/g4");
//        System.out.println(username+"--"+password);
//    }
//
//}