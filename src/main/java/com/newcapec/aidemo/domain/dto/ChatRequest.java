package com.newcapec.aidemo.domain.dto;

import lombok.Data;
import java.util.Map;

@Data
public class ChatRequest {
    // 哪个ai
    private String demoId;

    // 会话唯一标识
    private String conversationId;

    // 语言
    private String language;

    // 宽高比
    private String wh;

    // 清晰度
    private String definition;

    // 动画时长
    private Integer duration;

    // 配音音色
    private String audioType;

    // 面向群体（小学，高中）
    private String target;

    // 动画的主要用途（理解抽象逻辑）
    private String role;

    // 动画风格（2d）
    private String style;

    // 用户的第二次需求
    private String ements;

    // demo3:操作意图
    private String action = "REFINE";

    // 内容
    private String content;

    // 如新加demo则用扩展参数
    private Map<String, Object> params;
}