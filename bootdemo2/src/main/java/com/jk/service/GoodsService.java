/** 
 * <pre>项目名称:zly 
 * 文件名称:GoodsService.java 
 * 包名:com.jk.zyf.service.goods 
 * 创建日期:2019年3月22日下午8:27:07 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.service;

import com.jk.bean.CommentsBean;
import org.json.simple.JSONObject;

public interface GoodsService {

	//分页查询评论
	JSONObject findGoods(Integer page, Integer rows);

	//评论查询分页
	JSONObject queryPingLun(String goodId, Integer page, Integer rows, CommentsBean commentsBean);

	//新增评论
	void addPingLun(CommentsBean commentsBean);

	//新增评论回显在评论页面
	CommentsBean updateHuixian(String id);
    //mongoDB批量删除用户评论
    void deleteCommentsMongo(String[] ids);

    void sendSimpleMail(String userEmail, String 注册验证码, String message);
}
