/**
 * Copyright (C), 2019, 1809B
 * FileName: UserController1
 * Author:   zyf
 * Date:     2019/5/4 15:58
 * History:
 * zyf          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.aliyun.oss.model.OSSObject;
import com.jk.service.UserService1;
import com.jk.util.OssFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 月下故城
 * @create 2019/5/4
 * @since 1.0.0
 */
@Controller
@RequestMapping("user")
public class UserController1 {
    @Autowired
    private UserService1 userService1;
    //获取短信验证码
    @RequestMapping("gainMessgerCode")
    @ResponseBody
    public String  gainMessgerCode(String loginNumber) {
        return userService1.gainMessgerCode(loginNumber);
    }

    //短信验证码登录
    @RequestMapping("messagerlogin")
    @ResponseBody
    public String messagerlogin(String loginNumber, String password, HttpSession session) {
        return userService1.messagerlogin(loginNumber,password,session);
    }

    //oss图片下载
    //图片下载
    @RequestMapping("flieImgByID")
    @ResponseBody
    public  String downFile(String imgId, HttpServletResponse response) {
        //调用工具类
        OSSObject ossObject = OssFileUtils.downLoadImage(imgId);
        //给本地下载的时候生成文件名
        String fileName = new Date().toString().substring(0,5);
        //判断 返会文件件不为空
        if (ossObject != null) {
            //HTTPServletResponse 相应流
            InputStream inputStream = ossObject.getObjectContent();
            int buffer = 1024 * 10;
            byte data[] = new byte[buffer];
            try {
                response.setContentType("application/octet-stream");
                // 文件名可以任意指定
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//将文件名转为ASCLL编码
                int read;
                //迭代器
                while ((read = inputStream.read(data)) != -1) {
                    response.getOutputStream().write(data, 0, read);
                }
                response.getOutputStream().flush();//刷新
                response.getOutputStream().close();//关闭
                ossObject.close();//关闭
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}

