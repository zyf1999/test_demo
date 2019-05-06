/**
 * Copyright (C), 2019, 1809B
 * FileName: BookController
 * Author:   zyf
 * Date:     2019/4/28 13:49
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.jk.bean.Book;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/28
 * @since 1.0.0
 */
@RestController
public class BookController {
   @Autowired
   private BookService bookService;

    @Autowired
    private RedisTemplate redisTemplate;
   //分页查询
    @RequestMapping("findBook")
    public HashMap<String,Object> findBook(Integer rows,Integer page,Book book){
        HashMap<String,Object> hashMap = new HashMap<>();
        String key="book";
        if (!redisTemplate.hasKey(key)) {
            System.out.println("==走数据库22");
            hashMap=bookService.findBook(page,rows,book);
            redisTemplate.opsForValue().set(key, hashMap);
            redisTemplate.expire(key, 30, TimeUnit.NANOSECONDS);
        }else {
            System.out.println("走缓存22");
            hashMap=(HashMap<String, Object>) redisTemplate.opsForValue().get(key);
        }
        return hashMap;

       /* HashMap<String,Object> has=bookService.findBook(page,rows,book);
        return has;*/
    }

    //删除
   @RequestMapping("deleteAll")
    public Boolean deleteAll(@RequestParam("ids")Integer[] ids){
       try {
           bookService.deleteItem(ids);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
   }
   //新增
    @RequestMapping("saveBook")
    public void saveBook(Book book){
        if (book.getBookId() ==null) {
            bookService.saveBook(book);
        } else {
            bookService.updateBook(book);
        }
    }

    //回显
    @PostMapping("findBookById")
    public Book findBookById(@RequestParam("bookId") Integer bookId){
        Book book=bookService.findBookById(bookId);
        return book;
    }
}

