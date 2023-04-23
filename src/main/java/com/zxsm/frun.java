package com.zxsm;

/*
使用框架：springboot，slf4j日志框架

/
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class frun {
    public static void main(String[] args) {
        SpringApplication.run(frun.class,args);
        log.info("项目启动成功！！！");
    }
}
