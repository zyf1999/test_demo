/**
 * Copyright (C), 2019, 1809B
 * FileName: UserServiceImpl
 * Author:   zyf
 * Date:     2019/4/29 18:54
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.bean.User;
import com.jk.dao.UserMapper;
import com.jk.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/29
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService{
   @Autowired
    private UserMapper userMapper;
    //判断用户名和密码是否正确
    @Override
    public User getUserByUsernamePwd(User user) {
        return userMapper.getUserByUsernamePwd(user);
    }
    //注册
    @Override
    public Boolean save(User user) {
        //查看注册账号是否存在
        int count=userMapper.save(user.getUsername());
        //如果注册账号已经存在,执行下列代码
        if (count>0) {
            return false;
        }else {
            user.setPassword(user.getPassword());
            userMapper.add(user);
            return true;
        }
    }

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}

