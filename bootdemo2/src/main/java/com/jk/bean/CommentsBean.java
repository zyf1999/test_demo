/** 
 * <pre>项目名称:zyf 
 * 文件名称:CommentsBean.java 
 * 包名:com.jk.zyf.model.goods 
 * 创建日期:2019年3月13日上午9:11:58 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Document(collection="t_comments")
public class CommentsBean implements Serializable {

	private static final long serialVersionUID = -1236214485586139631L;
	private String id;
	
	private String comments;//评论内容
	 
	private String commentsName;//评论人
	
	private Integer commentsLevel;//1：好，2：一般，3差
	
	private Integer commentsStars;// 评论星数量
	
	private Integer count;
	
	private String goodsId;// 商品id
	
	private String goodsName;// 商品名称
     
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	private Date createtime;//时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startcreatetime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endcreatetime;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getStartcreatetime() {
		return startcreatetime;
	}

	public void setStartcreatetime(Date startcreatetime) {
		this.startcreatetime = startcreatetime;
	}

	public Date getEndcreatetime() {
		return endcreatetime;
	}

	public void setEndcreatetime(Date endcreatetime) {
		this.endcreatetime = endcreatetime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCommentsName() {
		return commentsName;
	}

	public void setCommentsName(String commentsName) {
		this.commentsName = commentsName;
	}

	public Integer getCommentsLevel() {
		return commentsLevel;
	}

	public void setCommentsLevel(Integer commentsLevel) {
		this.commentsLevel = commentsLevel;
	}

	public Integer getCommentsStars() {
		return commentsStars;
	}

	public void setCommentsStars(Integer commentsStars) {
		this.commentsStars = commentsStars;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "CommentsBean [id=" + id + ", comments=" + comments + ", commentsName=" + commentsName
				+ ", commentsLevel=" + commentsLevel + ", commentsStars=" + commentsStars + ", count=" + count
				+ ", goodsId=" + goodsId + ", goodsName=" + goodsName + ", createtime=" + createtime
				+ ", startcreatetime=" + startcreatetime + ", endcreatetime=" + endcreatetime + "]";
	}

}
