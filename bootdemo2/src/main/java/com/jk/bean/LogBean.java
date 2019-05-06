package com.jk.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "log_book")
public class LogBean {

    private String id;
    private Integer userId;
    private String username;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String ip;
    private String className;//类名
    private String method;//方法名
    private String reqParam;//请求
    private String repParam;//响应

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getRepParam() {
        return repParam;
    }

    public void setRepParam(String repParam) {
        this.repParam = repParam;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", createDate=" + createDate +
                ", ip='" + ip + '\'' +
                ", className='" + className + '\'' +
                ", method='" + method + '\'' +
                ", reqParam='" + reqParam + '\'' +
                ", repParam='" + repParam + '\'' +
                '}';
    }
}