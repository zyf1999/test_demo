/**
 * Copyright (C), 2019, 1809B
 * FileName: UserServiceImpl1
 * Author:   zyf
 * Date:     2019/5/4 15:59
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.bean.UserBean;
import com.jk.dao.UserMapper1;
import com.jk.util.HttpClientUtil;
import com.jk.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/5/4
 * @since 1.0.0
 */
@Service
public class UserServiceImpl1 implements UserService1{
 @Autowired
 private UserMapper1 userMapper1;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String gainMessgerCode(String loginNumber) {
        //验证手机号是否存在
        UserBean user = userMapper1.findUserByLoginNumber(loginNumber);
        if(user==null){
            userMapper1.insertloginNumber(loginNumber);
        }
        String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        HashMap<String, Object> params = new HashMap<>();
        String accountSid = "fd4615d571714be4a7a1155191586171";
        params.put("accountSid", accountSid);//开发者主账号ID（ACCOUNT SID）
        params.put("templateid", "1116707112");//短信模板ID
        //6位随机数，验证码
        int code = (int) Math.ceil(Math.random()*899999+100000);
        System.out.println(code);
        //String codeStr = String.valueOf(code);
        params.put("param", code);//短信模板中的变量值
        params.put("to", loginNumber);//手机号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(new Date());
        params.put("timestamp", time);//时间戳
        String token = "2ce6e5113b914efb9e37c975ad0f5849";
        String sig = Md5Util.getMd532(accountSid+token+time);
        params.put("sig", sig);//签名
        //发送短信
        String returnStr = HttpClientUtil.post(url, params);
        JSONObject parseObject = JSON.parseObject(returnStr);
        String respCode = parseObject.getString("respCode");

        if(!respCode.equals("00006")){
            return "发送短信验证码失败";
        }
        //存验证码
        String key = "msgcode"+loginNumber;
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        return "发送短信验证码成功";
    }

    @Override
    public String messagerlogin(String loginNumber, String password, HttpSession session) {
        //验证手机号是否存在
        UserBean user = userMapper1.findUserByLoginNumber(loginNumber);
        if(user==null){
            userMapper1.insertloginNumber(loginNumber);
        }
        //验证短信验证码是否正确
        //判断key是否存在
        String key = "msgcode"+loginNumber;
        if(!redisTemplate.hasKey(key)){
            return "验证码错误";
        }
        int code = (int) redisTemplate.opsForValue().get(key);
        String codeStr = String.valueOf(code);
        if(!password.equals(codeStr)){
            return "验证码错误";
        }
        //登录成功
        session.setAttribute("user", user);
        //清redis缓存
        redisTemplate.delete(key);
        return "登录成功";
    }
}

