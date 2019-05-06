package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //跳转前台页面
    @GetMapping("/page/easYui")
    public String easYui(){
        return  "easyui";
    }
    //跳转前台页面
    @GetMapping("/page/easYui1")
    public String easYui1(){
        return  "easyui1";
    }

    @GetMapping("/page/findgoodslist")
    public String findgoodslist(){
        return "goodslist";
    }
    //跳转手机短信登录
    @GetMapping("/page/findDeng")
    public String findDeng(){
        return "deng";
    }
    //跳转到注册
    @GetMapping("/page/findZhuCe")
    public String findZhuCe(){
        return "zhuce";
    }

    //跳转用户查询
    @GetMapping("/page/queryBook")
    public String queryUser(){
        return "book";
    }
    //新增
    @GetMapping("/page/toAddBook")
    public String toAddBook(){
        return "toAddBook";
    }
    //修改
    @GetMapping("/page/toUpdateBook")
    public String toUpdateBook(){
        return "toUpdateBook";
    }
    //登录
    @GetMapping("/page/findLogin")
    public String findLogin(){
        return "login";
    }
}
