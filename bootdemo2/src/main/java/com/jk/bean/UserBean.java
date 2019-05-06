/** 
 * <pre>项目名称:Maven 
 * 文件名称:UserBean.java 
 * 包名:com.jk.zyf.model.user 
 * 创建日期:2019年2月28日下午7:26:05 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -1372056069529459910L;
	private Integer id;
	
	private String name;
	
	private String loginNumber;
	
	private String password;
	
	private String sex;
	
	private Integer deptid;
	
	private String deptName;
	
	
	private String roleId;
	
	private String roleName;
	
	private String detail;
	
	private String email;
	
	private String headImg;
	
	private Integer province;
	
	private String provinceName;
	
	private Integer city;
	
	private String cityName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(String loginNumber) {
		this.loginNumber = loginNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", loginNumber=" + loginNumber + ", password=" + password
				+ ", sex=" + sex + ", deptid=" + deptid + ", deptName=" + deptName + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", detail=" + detail + ", email=" + email + ", headImg=" + headImg
				+ ", province=" + province + ", provinceName=" + provinceName + ", city=" + city + ", cityName="
				+ cityName + "]";
	}

	
}
