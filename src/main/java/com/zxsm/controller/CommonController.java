package com.zxsm.controller;

import com.zxsm.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${zxsm.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        log.info(originalFilename);
        //获取文件后缀
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用uuid防止文件重名，
        String fileName= UUID.randomUUID().toString()+suffix;
        //创建一个目录对象
        File dir =new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()){
            //目录不存在则创建
            dir.mkdir();
        }

        try{
            //将临时文件储存到指定位置
            file.transferTo(new File(basePath+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        //输入遛，通过输入流读取文件内容；
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件回写浏览器，在浏览器展示图片
            ServletOutputStream outputStream=response.getOutputStream();
            int len=0;
            byte[] bytes=new byte[1024];
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
