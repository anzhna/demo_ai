package com.newcapec.aidemo.controller;

import cn.hutool.core.io.IoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Tag(name = "通用功能接口")
@RestController
@RequestMapping("/api/v1/common")
@CrossOrigin(origins = "*")
public class CommonController {

    @Operation(summary = "下载静态资源文件")
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        try {
            // 1. 从 static 目录下读取文件 (例如: demo.mp4)
            ClassPathResource resource = new ClassPathResource("static/" + fileName);
            if (!resource.exists()) {
                throw new RuntimeException("文件不存在");
            }

            // 2. 设置强制下载的响应头 (关键步骤！)
            // attachment; filename="xxx.mp4" -> 告诉浏览器弹窗保存
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
            response.addHeader("Content-Length", "" + resource.contentLength());

            // 3. 开始像倒水一样把文件流输送给浏览器
            try (InputStream inputStream = resource.getInputStream()) {
                IoUtil.copy(inputStream, response.getOutputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 如果出错，简单的返回错误信息
            response.setStatus(500);
        }
    }
}
