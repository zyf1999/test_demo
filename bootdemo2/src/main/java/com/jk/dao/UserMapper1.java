/**
 * Copyright (C), 2019, 1809B
 * FileName: UserMapper1
 * Author:   zyf
 * Date:     2019/5/4 15:58
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.dao;

import com.jk.bean.UserBean;
import org.apache.ibatis.annotations.Insert;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/5/4
 * @since 1.0.0
 */
public interface UserMapper1 {
    //验证手机号是否存在
    UserBean findUserByLoginNumber(String loginNumber);

    /**
     * 新增手机号
     * @param loginNumber
     */
    @Insert("insert into  t_user (login_number) values (#{value})")
    void insertloginNumber(String loginNumber);
}
