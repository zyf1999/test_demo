/**
 * Copyright (C), 2019, 1809B
 * FileName: BookService
 * Author:   zyf
 * Date:     2019/4/28 13:51
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.bean.Book;
import com.jk.bean.TreeBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/28
 * @since 1.0.0
 */
public interface BookService {
    //查找树
    List<TreeBean> findTree();
    //分页查询
    HashMap<String, Object> findBook(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer rows, @RequestBody Book book);
    //删除
    void deleteItem(@RequestParam("ids") Integer[] ids);
    //新增
    void saveBook(@RequestBody Book book);
    //修改
    void updateBook(@RequestBody Book book);
    //回显
    Book findBookById(@RequestParam("bookId") Integer bookId);
}
