package com.jk.controller;


import com.jk.bean.TreeBean;
import com.jk.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TreeController {

    @Autowired
    private BookService bookService;

    //左侧树
    @PostMapping("/tree/findTree")
    @ResponseBody
    public List<TreeBean> findTree(){
        List<TreeBean> tree = bookService.findTree();
        return tree;
    }
}
