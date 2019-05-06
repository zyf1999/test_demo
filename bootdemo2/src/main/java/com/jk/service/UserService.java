/**
 * Copyright (C), 2019, 1809B
 * FileName: UserService
 * Author:   zyf
 * Date:     2019/4/29 18:54
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.bean.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/29
 * @since 1.0.0
 */
public interface UserService {
    //判断用户名和密码是否正确
    User getUserByUsernamePwd(User user);
    //注册
    Boolean save(User user);

    /**
     * 去后台查是否有此账号
     * @param username
     * @return
     */
    User queryUserByName(String username);
}
