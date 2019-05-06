/** 
 * <pre>项目名称:zly 
 * 文件名称:GoodsServiceImpl.java 
 * 包名:com.jk.zyf.service.goods 
 * 创建日期:2019年3月22日下午8:27:27 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.Date;
import java.util.List;

import com.jk.bean.CommentsBean;
import com.jk.bean.GoodsBean;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import javax.annotation.Resource;

@Service
public class GoodsServiceImpl implements GoodsService {
	/* @Autowired
	  private RedisTemplate<String, Object> redisTemplate;*/
	/* @Autowired
	  private GoodsMapper goodsMapper;*/
	 @Autowired
	  private MongoTemplate mongoTemplate;
	//分页查询评论
	@Override
	public JSONObject findGoods(Integer page, Integer rows) {
		Query query = new Query();
		JSONObject jsonObject = new JSONObject();
	    //查询数量
		long total = mongoTemplate.count(query, GoodsBean.class);
		//起始条数
		int start = (page-1)*rows;
		//分页
		query.skip(start).limit(rows);
		//查询数据
		List<GoodsBean> find2 = mongoTemplate.find(query, GoodsBean.class);
	    for (int i = 0; i < find2.size(); i++) {
	    	Query query2 = new Query();
	    	//获取每一行的id
	    	String id = find2.get(i).getId();
	    	//根据id查询对应评论表的数量
	    	query2.addCriteria(Criteria.where("goodsId").is(id));
	    	Integer count2 = (int) mongoTemplate.count(query2, CommentsBean.class);
	    	find2.get(i).setCount(count2);
		}
		jsonObject.put("total", total);
		jsonObject.put("rows", find2);
	return jsonObject;
	}
	
	
//评论分页
	@Override
	public JSONObject queryPingLun(String goodId, Integer page, Integer rows, CommentsBean commentsBean) {
		Query query = new Query();
		JSONObject jsonObject = new JSONObject();
		if(StringUtils.isNotEmpty(commentsBean.getComments())) {
			query.addCriteria(Criteria.where("comments").regex(commentsBean.getComments()));
		}
		if(commentsBean.getStartcreatetime() != null && commentsBean.getEndcreatetime()!= null) {
			query.addCriteria(Criteria.where("createtime").gt(commentsBean.getStartcreatetime()).lte(commentsBean.getEndcreatetime()));
		}

		//根据id查询
		query.addCriteria(Criteria.where("goodsId").is(goodId));
		//查询数量
		long count = mongoTemplate.count(query, CommentsBean.class);
		//起始条数
		int start = (page-1)*rows;
		//分页
		query.skip(start).limit(rows);
		//查询数据
		List<CommentsBean> find = mongoTemplate.find(query, CommentsBean.class);
		jsonObject.put("total", count);
		jsonObject.put("rows", find);
		return jsonObject;
	}

//新增评论
	@Override
	public void addPingLun(CommentsBean commentsBean) {
		if(commentsBean.getId() == "") {
			commentsBean.setId(null);
		}
		commentsBean.setCreatetime(new Date());
		mongoTemplate.save(commentsBean);
	}
	
//新增评论回显在评论页面
	@Override
	public CommentsBean updateHuixian(String id) {
		CommentsBean findById = mongoTemplate.findById(id, CommentsBean.class);
		return findById;
	}
	//mongoDB批量删除用户评论
	@Override
	public void deleteCommentsMongo(String[] ids) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").in(ids));
		mongoTemplate.remove(query, CommentsBean.class);
	}
	@Resource
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String from;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	//发送普通邮件
	@Override
	public void sendSimpleMail(String to, String title, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
		logger.info("邮件发送成功");
	}
}
