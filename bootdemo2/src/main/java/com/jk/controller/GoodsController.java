/** 
 * <pre>项目名称:zly 
 * 文件名称:GoodsController.java 
 * 包名:com.jk.zyf.controller.goods 
 * 创建日期:2019年3月22日下午8:26:33 
 * Copyright (c) 2019, All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import com.jk.bean.CommentsBean;
import com.jk.service.GoodsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
	private GoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
   // 分页查询评论
    @RequestMapping("findGoods")
    @ResponseBody
    public JSONObject findGoods(Integer page, Integer rows) {
        if(redisTemplate.hasKey(rows+page)){
           redisTemplate.opsForValue().get(rows + page);
            System.out.println("走缓存1");
          /*  redisTemplate.expire(rows+page, 2, TimeUnit.MINUTES);*/
        }
            return goodsService.findGoods(page,rows);

    }
   //评论分页
    @RequestMapping("queryPingLun")
    @ResponseBody
    public JSONObject queryPingLun(String goodId, Integer page, Integer rows, CommentsBean commentsBean) {
       /* JSONObject chaxun;
        if(redisTemplate.hasKey(rows+page)){
             chaxun = (JSONObject) redisTemplate.opsForValue().get(rows + page);
            System.out.println("走缓存");
        }else{*/
        JSONObject chaxun=goodsService.queryPingLun(goodId,page,rows,commentsBean);
        /*    redisTemplate.opsForValue().set(rows+page,chaxun);
            System.out.println("走数据库");
        }*/
  		return chaxun;

  	}
    //新增评论
    @RequestMapping("addPingLun")
    @ResponseBody
    public Boolean addPingLun(CommentsBean commentsBean) {
  		try {
  			goodsService.addPingLun(commentsBean);
  			return true;
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  			return false;
  		}
  	}
    //新增评论回显在评论页面
    @RequestMapping("updateHuixian")
    @ResponseBody
    public CommentsBean updateHuixian(String id) {
  		CommentsBean updhuixian = goodsService.updateHuixian(id);
  		return updhuixian;
  	}
    //mongoDB批量删除用户评论
    @RequestMapping("deleteshanall")
    @ResponseBody
    public Boolean deleteshanall(String[] ids){
        try {
            goodsService.deleteCommentsMongo(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //发送验证码的  验证码存储在redis中设置三分钟过去时间
    @RequestMapping("getCheckCode")
    @ResponseBody
    public String getCheckCode(String userEmail, HttpServletRequest HttpServletRequest){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "商品用户注册验证码为："+checkCode+"验证码时效为三分钟";
        try {
            goodsService.sendSimpleMail(userEmail, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        HttpServletRequest.getSession().setAttribute("checkCode",checkCode);
        return checkCode;
    }
}
