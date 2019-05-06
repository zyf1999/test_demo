/**
 * Copyright (C), 2019, 1809B
 * FileName: BookServiceImpl
 * Author:   zyf
 * Date:     2019/4/28 13:52
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.jk.bean.Book;
import com.jk.bean.TreeBean;
import com.jk.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;
    //查找树
    @Override
    public List<TreeBean> findTree(){
        int pid=0;
        List<TreeBean> list = getTreeBeans(pid);
        return list;
    }

    @Override
    public HashMap<String, Object> findBook(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer rows, @RequestBody Book book) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int count=bookMapper.queryCount(book);
        int start=(page-1)*rows;
        List<Book> list=bookMapper.queryUserList(start,rows,book);
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;
    }
    //删除
    @Override
    public void deleteItem(@RequestParam("ids")Integer[] ids) {
        bookMapper.deleteItem(ids);
    }
    //新增
    @Override
    public void saveBook(@RequestBody Book book) {
        bookMapper.saveBook(book);
    }
    //修改
    @Override
    public void updateBook(@RequestBody Book book) {
        bookMapper.updateBook(book);
    }
    //回显
    @Override
    public Book findBookById(@RequestParam("bookId")Integer bookId) {
        return bookMapper.findBookById(bookId);
    }

    private List<TreeBean> getTreeBeans(int pid) {
        List<TreeBean> list = bookMapper.findTree(pid);
        for (TreeBean treeBean : list) {
            Integer id = treeBean.getId();
            List<TreeBean> nodes = getTreeBeans(id);
            treeBean.setChildren(nodes);
        }
        return list;
    }
}

