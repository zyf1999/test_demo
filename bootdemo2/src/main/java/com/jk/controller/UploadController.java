package com.jk.controller;

import com.aliyun.oss.OSSClient;
import com.jk.util.AliyunOSSClientUtil;
import com.jk.util.OSSClientConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadController {

    @RequestMapping("upload")
    @ResponseBody
    //return "upload/"+onlyFileName;
    public String upImg(MultipartFile image, HttpServletRequest req) throws Exception{
        //获取原文件名称
        String fileName = image.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String folderPath = req.getSession().getServletContext().getRealPath("/")+
                "upload/";
        File file = new File(folderPath);
        //该目录是否已经存在
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }
        String onlyFileName = sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        FileOutputStream fos = new FileOutputStream(folderPath+onlyFileName);
        fos.write(image.getBytes());
        fos.flush();
        fos.close();
        System.out.println(file);

        //得到真正的路径
        File file2 = new File(file+"\\"+onlyFileName);

        //初始化OSSClient
        OSSClient ossClient= AliyunOSSClientUtil.getOSSClient();
        //Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);

        //System.out.println(expiration.toString()+"+expiration");
        AliyunOSSClientUtil.uploadObject2OSS(ossClient, file2, OSSClientConstants.BACKET_NAME, OSSClientConstants.FOLDER);
        String url = "自己的服务器地址/自己的文件夹名/"+onlyFileName;
        //String url = ossClient.generatePresignedUrl(BACKET_NAME, onlyFileName, expiration).toString();
        return url;

    }

}