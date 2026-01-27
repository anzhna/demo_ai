package com.newcapec.aidemo.controller;

import com.newcapec.aidemo.domain.dto.ChatRequest;
import com.newcapec.aidemo.domain.dto.ChatResponse;
import com.newcapec.aidemo.service.Demo1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ai对话接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") //允许跨域
public class DemoController {

    private final Demo1Service demo1Service;

    @Operation(summary = "ai对话接口",description = "用于确认aiDemo并对话")
    @PostMapping("/chat/completions")
    public ChatResponse dialogue(
            @RequestHeader("X-Session-ID") String sessionId,
            @RequestBody ChatRequest chatRequest) {
        String demoId = chatRequest.getDemoId();
        if (demoId == null) {
            return ChatResponse.error("系统错误,请刷新后重试");
        }
        if (demoId.equals("demo1")){
            demo1Service.createChatRequest(sessionId,chatRequest);
        }
        return ChatResponse.success("成功");
    }
}
