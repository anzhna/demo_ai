package com.newcapec.aidemo.domain.entity;

import lombok.Data;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class UserSession {
    private String sessionId;
    private long lastActiveTime;

    /**
     * Key: conversationId (会话ID) -> 也就是侧边栏的每一项
     * Value: DemoContext (存具体的聊天记录)
     */
    private ConcurrentHashMap<String, DemoContext> conversationMap = new ConcurrentHashMap<>();

    // 资源文件存储
    private ConcurrentHashMap<String, byte[]> resourceMap = new ConcurrentHashMap<>();

    public UserSession(String sessionId) {
        this.sessionId = sessionId;
        this.refreshTime();
    }

    public void refreshTime() {
        this.lastActiveTime = System.currentTimeMillis();
    }

    /**
     * 获取或创建会话
     * @param conversationId 前端传来的会话ID
     * @param demoId 业务类型 (仅创建新会话时需要)
     * @param defaultTitle 默认标题 (仅创建新会话时需要)
     */
    public DemoContext getConversation(String conversationId, String demoId, String defaultTitle) {
        return conversationMap.computeIfAbsent(conversationId, k -> { //computeIfAbsent是线程安全的
            // 如果是新的，就 new 一个，并记录它是属于哪个 ai demo 的
            return new DemoContext(demoId,conversationId, defaultTitle);
        });
    }

    // 用于查询历史，不存在则返回 null
    public DemoContext getConversation(String conversationId) {
        return conversationMap.get(conversationId);
    }
}