/**
 * Copyright (C), 2019, 1809B
 * FileName: UserMapper
 * Author:   zyf
 * Date:     2019/4/29 18:53
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.dao;

import com.jk.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/29
 * @since 1.0.0
 */
public interface UserMapper {
    //判断用户名和密码是否正确
    @Select("select * from 1810_user where username=#{username} and password=#{password}")
    User getUserByUsernamePwd(User user);
    //查看注册账号是否存在
    @Select(" select count(1) from 1810_user where username=#{username} ")
    int save(String username);
    @Insert("insert into 1810_user(username,password) values(#{username},#{password})")
    void add(User user);

    /**
     * 查看是否有此账号
     * @param username
     * @return
     */
    @Select("select * from 1810_user where username = #{value}")
    User queryUserByName(String username);
}
