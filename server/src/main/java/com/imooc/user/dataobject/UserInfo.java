package com.imooc.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @company xxx
 * @date 2019年07月07日 21:39 胡晓磊 Exp $
 */
@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
