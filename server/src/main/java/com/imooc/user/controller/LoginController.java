package com.imooc.user.controller;

import com.imooc.user.VO.ResultVO;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.service.UserService;
import com.imooc.user.utils.CookieUtil;
import com.imooc.user.utils.ResultVOUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月07日 21:46 胡晓磊 Exp $
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @return com.imooc.user.VO.ResultVO
     * @author 胡晓磊
     * @version 1.0
     * @date 2019-07-09
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        // 1. openid 和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenId(openid);

        if (null == userInfo) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2. 判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3. cookie里设置 openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }


    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid
            , HttpServletRequest request
            , HttpServletResponse response) {
        // 判断是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);


        if (cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 1. openid 和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenId(openid);

        if (null == userInfo) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2. 判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3. redis设置key
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token)
                , openid, expire, TimeUnit.SECONDS);

        // 3. cookie里设置 token=xyz
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVOUtil.success();
    }
}
