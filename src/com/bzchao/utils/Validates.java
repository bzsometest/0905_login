package com.bzchao.utils;

public class Validates {
    public static  boolean isEmpty(String str){
        if(str==null){
            return  true;
        }
        if("".equals(str)){
            return  true;
        }
        return  false;
    }
}
