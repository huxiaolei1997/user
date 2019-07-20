package com.imooc.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月09日 21:49 胡晓磊 Exp $
 */
public class CookieUtil {


    /**
     * 设置 cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);

        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
