/** 
 * <pre>项目名称:zyf 
 * 文件名称:GoodsBean.java 
 * 包名:com.jk.zyf.model.goods 
 * 创建日期:2019年3月13日上午9:01:22 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document(collection="t_goods")
public class GoodsBean implements Serializable {

	private static final long serialVersionUID = 1050348222224297126L;
	private String id;
	
	private String name;
    
	private Integer count;

	private  String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GoodsBean [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
