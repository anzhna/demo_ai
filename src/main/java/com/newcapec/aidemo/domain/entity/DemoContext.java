package com.newcapec.aidemo.domain.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DemoContext {
    // 记录自己属于哪个 Demo
    private String demoId;

    //存一下自己的会话标签
    private String conversationId;

    //会话标题 (侧边栏展示用，例如 "帮我生成红球...")
    private String title;

    //创建时间 (用于侧边栏排序)
    private long createTime;

    // 历史消息列表
    private List<ChatMessage> history = new ArrayList<>();

    // 状态机状态
    private String state = "IDLE";
    private String cachedPrompt;

    // 构造函数
    public DemoContext(String demoId, String conversationId, String title) {
        this.conversationId = conversationId;
        this.demoId = demoId;
        this.title = title;
        this.createTime = System.currentTimeMillis();
    }
    // 添加用户消息
    public void addChatMessage(ChatMessage chatMessage) {
        this.history.add(chatMessage);
    }
    // 快捷添加用户消息
    public void addUserMessage(String content) {
        this.history.add(new ChatMessage("user", "text", content, null, System.currentTimeMillis()));
    }
    // 快捷添加ai消息
    public void addAiMessage(String content) {
        this.history.add(new ChatMessage("assistant", "text", content, null, System.currentTimeMillis()));
    }
    // 快捷添加ai资源卡片
    public void addAiResource(String content, String resourceId) {
        this.history.add(new ChatMessage("assistant", "resource", content, resourceId, System.currentTimeMillis()));
    }
}