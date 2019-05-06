/**
 * Copyright (C), 2019, 1809B
 * FileName: UserController
 * Author:   zyf
 * Date:     2019/4/29 18:53
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.jk.bean.User;
import com.jk.bean.UserBean;
import com.jk.service.UserService;
import com.jk.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/29
 * @since 1.0.0
 */
@Controller
public class UserController {
  @Autowired
    UserService userService;
  @RequestMapping("toLogin")
    public String index(HttpServletRequest request, Model model){
      Cookie[] cookies = request.getCookies();
      if(cookies !=null){
          for (Cookie cookie : cookies){
              if(cookie.getName().equals(Constant.cookieNamePwd)){
                  String value = cookie.getValue();
                  if(value!=null){
                      String[] split = value.split(Constant.splitChart);
                      model.addAttribute("username", split[0]);
                      model.addAttribute("password", split[1]);
                  }
              }
          }
      }
      return "login";
  }

  @RequestMapping("login")
    public String login(HttpServletResponse response, User user, Model model, HttpSession session){
      //判断用户名和密码是否正确
      User userFromDb = userService.getUserByUsernamePwd(user);
      if(userFromDb!=null){
          //正确 判断是否记住密码
          if(user.getRemPwd()!=null){
              //是  用户名和密码都存到cookie中去
              Cookie cookie =  new Cookie(Constant.cookieNamePwd,user.getUsername()+ Constant.splitChart + user.getPassword());
              cookie.setMaxAge(604800);
              response.addCookie(cookie);
          }else {
              //否 清除cookie
             Cookie cookie = new Cookie(Constant.cookieNamePwd,"");
             cookie.setMaxAge(0);
             response.addCookie(cookie);
          }
          session.removeAttribute("msg");
      }else{
          //密码输入错误
          session.setAttribute("msg","密码输入错误");
          Cookie cookie = new Cookie(Constant.cookieNamePwd,"");
          cookie.setMaxAge(0);
          response.addCookie(cookie);
          return "redirect:toLogin";
      }
      return "easyui";
  }
    //注册
    @RequestMapping("zhuCe")
    @ResponseBody
    public Boolean zhuce(User user) {
        try {
            return userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

