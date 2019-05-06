/**
 * Copyright (C), 2019, 1809B
 * FileName: User
 * Author:   zyf
 * Date:     2019/4/29 16:50
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.bean;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/29
 * @since 1.0.0
 */
@Data
public class User {
    private Integer id;
  private String username;
  private String email;
  private String password;
  private Integer age;
  private String remPwd;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemPwd() {
        return remPwd;
    }

    public void setRemPwd(String remPwd) {
        this.remPwd = remPwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

