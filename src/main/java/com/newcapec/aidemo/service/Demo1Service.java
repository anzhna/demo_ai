package com.newcapec.aidemo.service;

import com.newcapec.aidemo.domain.dto.ChatRequest;
import com.newcapec.aidemo.domain.entity.ChatMessage;

import java.util.List;


public interface Demo1Service {

    ChatMessage createChatRequest(String sessionId,ChatRequest chatRequest);
}
