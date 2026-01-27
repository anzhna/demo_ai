package com.newcapec.aidemo.controller;


import com.newcapec.aidemo.domain.dto.ChatResponse;
import com.newcapec.aidemo.service.HistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "历史记录相关接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1") //允许跨域
public class HistoryController {

    private final HistoryService historyService;

    //获取所有历史
    @GetMapping("/conversations")
    public ChatResponse getHistory(@RequestHeader("X-Session-ID") String sessionId,
                                   @RequestParam("demoId") String demoId) {
        return ChatResponse.success(historyService.getHistory(sessionId, demoId));
    }

    //获取对应历史对话
    @GetMapping("/conversation/history")
    public ChatResponse getChat(@RequestHeader("X-Session-ID") String sessionId,
                                @RequestParam("conversationId") String conversationId){
        return ChatResponse.success(historyService.getChat(sessionId, conversationId));
    }
}
