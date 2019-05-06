/**
 * Copyright (C), 2019, 1809B
 * FileName: UserController
 * Author:   zyf
 * Date:     2019/4/28 10:23
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/28
 * @since 1.0.0
 */
@RestController
public class UserController {
  @RequestMapping("hello")
    public String hello(){
      return "欢迎你 springboot";
  }
}

