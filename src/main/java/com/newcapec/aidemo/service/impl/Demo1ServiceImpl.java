package com.newcapec.aidemo.service.impl;

import com.newcapec.aidemo.domain.dto.ChatRequest;
import com.newcapec.aidemo.domain.entity.ChatMessage;
import com.newcapec.aidemo.domain.entity.DemoContext;
import com.newcapec.aidemo.domain.entity.UserSession;
import com.newcapec.aidemo.repository.MemoryRepository;
import com.newcapec.aidemo.service.Demo1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Demo1ServiceImpl implements Demo1Service {

    private final MemoryRepository memoryRepository;

    @Override
    public ChatMessage createChatRequest(String sessionId, ChatRequest chatRequest) {
        ChatMessage chatMessageUser = ChatMessage.builder()
                .role("user")
                .type("text")
                .content(chatRequest.getContent())
                .time(System.currentTimeMillis())
                .build();
        // 拿取用户信息，如不存在自动创建
        UserSession userSession = memoryRepository.getSession(sessionId);

        // 从用户信息中拿取对应会话信息，如不存在自动创建
        DemoContext demoContext = userSession.getConversation(chatRequest.getConversationId(), chatRequest.getDemoId(), chatRequest.getContent());

        // 存入用户消息
        demoContext.addChatMessage(chatMessageUser);

        try {
            // 模拟一点延迟
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String videoUrl = "http://localhost:8080/demo.mp4";

        ChatMessage chatMessageAi = ChatMessage.builder()
                .role("assistant")
                .type("video")
                .content("教学视频已生成。")
                .resourceId(videoUrl) // 把 URL 塞进去
                .time(System.currentTimeMillis())
                .build();

        demoContext.addChatMessage(chatMessageAi);
        return chatMessageAi;
    }
}

