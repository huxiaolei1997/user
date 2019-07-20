package com.imooc.user.service.impl;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.repository.UserInfoRepository;
import com.imooc.user.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月07日 21:45 胡晓磊 Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenId(String openId) {
        return userInfoRepository.findByOpenid(openId);
    }
}
