package com.bzchao.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 商品pid处理工具类
 */
public class ProductPidUtils {
    /**
     * 根据商品字符串数组获得商品整形数组
     *
     * @param pidStringArray
     * @return
     */
    public static int[] getPidArray(String[] pidStringArray) {
        int[] pidArray = new int[pidStringArray.length];
        for (int i = 0; i < pidStringArray.length; i++) {
            int pid = Integer.valueOf(pidStringArray[i]);
            pidArray[i] = pid;
        }
        return pidArray;
    }

    /**
     * 获得商品pid字符串数组
     *
     * @param req
     * @return
     */
    public static String[] getPidStringArray(HttpServletRequest req) {
        Cookie historyCookie = CookieUtils.getCookie(CookieUtils.HISTORY, req.getCookies());
        if (historyCookie == null) {//第一次访问商品
            return new String[]{};
        }
        return historyCookie.getValue().split("-");
    }

    /**
     * 将此商品添加到历史记录中(有序)
     *
     * @param pidStringArray
     * @param newPidString
     * @return
     */
    public static String[] addPid(String[] pidStringArray, String newPidString) {
        if (pidStringArray == null) {
            pidStringArray = new String[]{};
        }

        Collection<String> collection = Arrays.asList(pidStringArray);
        LinkedList<String> linkedList = new LinkedList<>(collection);

        if (linkedList.contains(newPidString)) {//历史记录中存在此商品
            linkedList.remove(newPidString);
            linkedList.addFirst(newPidString);
        } else {//历史记录中不存在此商品
            if (linkedList.size() >= 4) {//商品数量达到四个则最后一个
                linkedList.removeLast();
            }
            //添加商品到第一个
            linkedList.addFirst(newPidString);
        }
        //TODo 此处需要验证，list装数组
        return linkedList.toArray(new String[]{});
    }

    //生成商品pid字符串
    public static String getPidString(String[] pidStringArray) {
        return String.join("-", pidStringArray);
    }
}
