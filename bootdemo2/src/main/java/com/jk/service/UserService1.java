/**
 * Copyright (C), 2019, 1809B
 * FileName: UserService1
 * Author:   zyf
 * Date:     2019/5/4 15:58
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.service;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/5/4
 * @since 1.0.0
 */
public interface UserService1 {
    //获取短信验证码
    String gainMessgerCode(String loginNumber);
    //短信验证码登录
    String messagerlogin(String loginNumber, String password, HttpSession session);
}
