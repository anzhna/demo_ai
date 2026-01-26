package com.newcapec.aidemo.controller;

import com.newcapec.aidemo.domain.dto.ChatRequest;
import com.newcapec.aidemo.domain.dto.ChatResponse;
import com.newcapec.aidemo.service.Demo1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "ai对话接口")
@Controller
@RequiredArgsConstructor
public class DemoController {

    private final Demo1Service demo1Service;

    @Operation(summary = "ai对话接口",description = "用于确认aiDemo并对话")
    @PostMapping("/dialogue")
    public ChatResponse dialogue(
            @RequestHeader("X-Session-ID") String sessionId,
            @PathVariable ChatRequest chatRequest) {
        String demoId = chatRequest.getDemoId();
        if (demoId == null) {
            return ChatResponse.error(0,"系统错误");
        }
        if (demoId.equals("demo1")){
            demo1Service.createChatRequest(sessionId,chatRequest);
        }
    }
}
