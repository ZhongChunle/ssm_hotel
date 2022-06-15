package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.zcl.utils.SystemConstant;
import com.zcl.utils.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 项目名称：ssm_hotel
 * 描述：图片上传共有控制器
 *
 * @author zhong
 * @date 2022-05-21 12:17
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {
    /**
     * 添加房间类型图片上传控制器接口
     * @param attach 与上传文件的field对应
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file")MultipartFile attach){
        // 创建map集合返回layui给定发返回json格式数据
        HashMap<String, Object> imgMap = new HashMap<String, Object>();
        // 判断是否有选中数据
        if(!attach.isEmpty()){
            // 获取文件上传的地址
            // 获取源文件的名称
            String oldFileName = attach.getOriginalFilename();
            // 获取文件的后缀名
            String extension = FilenameUtils.getExtension(oldFileName);
            // 重命名旧文件
            String newFileName = UUIDUtils.randomUUID() + "." +extension;
            // 解决同一个文件夹下图片文件过多问题，使用日期作为单独的文件夹存储
            String datePath = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
            // 组装最终的文件名称
            String finalName = datePath + "/" + newFileName;
            // 创建文件对象【文件上传地址,文件名称】
            File dest = new File(SystemConstant.IMAGE_UPLOAD_PATH, finalName);
            // 判断文件夹是否存在，不存在就创建
            if(!dest.getParentFile().exists()){
                // 创建文件夹
                dest.getParentFile().mkdirs();
            }
            try {
                // 进行文件上传
                attach.transferTo(dest);
                // 返回layui的json数据响应格式
                imgMap.put("code",0);
                imgMap.put("msg","图片上传成功");
                HashMap<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("src","/hotel/show/"+finalName);
                imgMap.put("data",dataMap);
                imgMap.put("imagePath",finalName);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(imgMap);
    }
}
