/**
 * Copyright (C), 2019, 1809B
 * FileName: MyRealm
 * Author:   zyf
 * Date:     2019/5/4 19:24
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.shiro;


import com.jk.bean.User;
import com.jk.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class MyRealm extends AuthorizingRealm {

    //注入Servie层
    @Autowired
    private UserService userService;

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取到输入框输入的用户名
        String username = (String)token.getPrincipal();
        //去后台查是否有此账号
        User user = userService.queryUserByName(username);
        //判断账号是否存在
        if(user == null){
            //如果用户对象为空 抛出用户名错误异常
            throw new UnknownAccountException();
        }
        //认证器 第一个参数为用户名或用户对象 第二个参数为密码 第三个参数为当前real名称
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),this.getName());
        return simpleAuthenticationInfo;
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}


