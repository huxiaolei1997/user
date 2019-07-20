package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月07日 21:44 胡晓磊 Exp $
 */
public interface UserService {

    UserInfo findByOpenId(String openId);
}
