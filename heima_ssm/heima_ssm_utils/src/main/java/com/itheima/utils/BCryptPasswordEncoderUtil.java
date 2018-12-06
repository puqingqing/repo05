package com.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {

   public static  BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String getendcode(String ssm){
        String encode = bCryptPasswordEncoder.encode(ssm);
        return encode;
    }

    public static  void main(String []arg){
        String zhangsan = getendcode("zhangsan");
        System.out.println(zhangsan);
    }
}
