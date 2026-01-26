package com.newcapec.aidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //定时任务处理
public class AiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiDemoApplication.class, args);
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\t(♥◠‿◠)ﾉﾞ  新开普 AI 演示平台启动成功   ლ(´ڡ`ლ)ﾞ");
        System.out.println("----------------------------------------------------------");
    }
}
