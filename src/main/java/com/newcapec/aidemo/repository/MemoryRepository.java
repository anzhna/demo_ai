package com.newcapec.aidemo.repository;

import com.newcapec.aidemo.domain.entity.UserSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存仓库管理员
 * 作用：管理所有的 UserSession
 */
@Repository // Spring 才能扫描，并把它注入到 Controller
public class MemoryRepository {

    // 这里存的是所有在线用户的 Session
    // Key: 用户ID (X-Session-ID)
    // Value: 用户的完整数据 (UserSession)
    private final Map<String, UserSession> sessionStore = new ConcurrentHashMap<>();

    //凭票取包
    public UserSession getSession(String sessionId) {
        // 如果这个 ID 已经有记录了，直接返回
        // 如果没有，就 new 一个新的存进去，再返回
        return sessionStore.computeIfAbsent(sessionId, k -> new UserSession(k));
    }

    // 定时清理过期数据 (每10分钟查一次)
    // 防止内存爆炸，把超过10小时没动静的人踢掉
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void cleanExpiredSessions() {
        long now = System.currentTimeMillis();
        long timeout = 10 * 60 * 60 * 1000; // 30分钟

        sessionStore.entrySet().removeIf(entry -> {
            UserSession session = entry.getValue();
            // 如果 (当前时间 - 最后活跃时间) > 超时时间，就删掉
            return (now - session.getLastActiveTime()) > timeout;
        });
        System.out.println("执行清理任务，当前在线人数: " + sessionStore.size());
    }
}
