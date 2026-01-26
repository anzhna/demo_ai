package com.newcapec.aidemo.domain.dto;

import lombok.Data;
import java.util.Map;

@Data
public class ChatRequest {
    // 哪个ai
    private String demoId;

    // 会话唯一标识
    private String conversationId;

    // demo3:操作意图
    private String action = "REFINE";

    // 内容
    private String content;

    // 如新加demo则用扩展参数
    private Map<String, Object> params;
}