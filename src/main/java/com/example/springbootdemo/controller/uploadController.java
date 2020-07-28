package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.ProgressEntity;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(value = "uploadController", tags={"文件上传API"})
@Controller
public class uploadController {
    //文件上传
    @RequestMapping(value = "upload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file){
        Map<String,Object> result = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String filename = file.getOriginalFilename();
        int i = filename.lastIndexOf(".");
        String extName = filename.substring(i);
        String files = uuid+extName;
        if (file != null && !file.isEmpty()){
            try {
                file.transferTo(new File("d:/yx/"+files));
                result.put("code",200);
                result.put("msg","success");
            }catch (IOException e){
                result.put("code",-1);
                result.put("msg","文件上传出错");
                e.printStackTrace();
            }
        }else{
            result.put("code",-1);
            result.put("msg","为获取到文件");
        }
        System.out.println("输出:"+result);
        return result;
    }

    /**
     * 获取上传进度
     */
    @RequestMapping("getUploadProgress")
    @ResponseBody
    public ProgressEntity getUploadProgress(HttpServletRequest request){
        return (ProgressEntity) request.getSession().getAttribute("uploadStatus");
    }
}
