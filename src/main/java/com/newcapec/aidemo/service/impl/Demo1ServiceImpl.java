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
        DemoContext demoContext = userSession.getConversation(chatRequest.getConversationId(), chatRequest.getDemoId(), "123");
        // 在会话信息中添加用户新对话
        demoContext.addChatMessage(chatMessageUser);
        //调用接口——————————————————————————————————————————
        return chatMessageUser;
    }
}

