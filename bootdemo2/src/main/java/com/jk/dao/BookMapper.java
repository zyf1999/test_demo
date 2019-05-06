/**
 * Copyright (C), 2019, 1809B
 * FileName: BookMapper
 * Author:   zyf
 * Date:     2019/4/28 13:50
 * History:
 * <author>          <time>          <version>          <desc>
 * zyf           修改时间           版本号              描述
 */
package com.jk.dao;

import com.jk.bean.Book;
import com.jk.bean.TreeBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/4/28
 * @since 1.0.0
 */
public interface BookMapper {
    //查询数量
    int queryCount(@Param("book") Book book);
    //分页查询
    List<Book> queryUserList(@Param("start") int start, @Param("rows") Integer rows, @Param("book") Book book);
    //查询左侧树
    @Select(value = " select * from goods_tree where pid=#{value} ")
    List<TreeBean> findTree(int pid);
    //删除
    void deleteItem(@RequestParam("ids")  Integer[] ids);
    //新增
    void saveBook(@RequestBody   Book book);
    //修改
    void updateBook(@RequestBody  Book book);
    //回显
    @Select(value = " select * from 1810_book where bookId=#{value}")
    Book findBookById(@RequestParam("bookId") Integer bookId);
}
