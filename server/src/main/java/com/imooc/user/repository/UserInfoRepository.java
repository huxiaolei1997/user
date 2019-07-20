package com.imooc.user.repository;

import com.imooc.user.dataobject.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月07日 21:42 胡晓磊 Exp $
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openId);
}
