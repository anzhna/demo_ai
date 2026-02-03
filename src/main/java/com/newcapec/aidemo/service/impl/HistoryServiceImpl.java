package com.newcapec.aidemo.service.impl;

import com.newcapec.aidemo.domain.entity.DemoContext;
import com.newcapec.aidemo.domain.entity.UserSession;
import com.newcapec.aidemo.domain.vo.ContextVO;
import com.newcapec.aidemo.domain.vo.ConversationVO;
import com.newcapec.aidemo.repository.MemoryRepository;
import com.newcapec.aidemo.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final MemoryRepository memoryRepository;

    @Override
    public List<ConversationVO> getHistory(String sessionId, String demoId) {

        UserSession userSession = memoryRepository.getSession(sessionId);

        return userSession.getConversationMap().values().stream() // 拿到所有书
                // 只保留 demoId 等于目标id的
                .filter(ctx -> ctx.getDemoId().equals(demoId))

                // 把后端实体转成前端要的 VO 对象
                .map(ctx -> new ConversationVO(
                        ctx.getConversationId(),
                        ctx.getDemoId(),
                        ctx.getTitle(),
                        ctx.getCreateTime()
                ))

                // 按时间倒序,新的在上面
                .sorted((a, b) -> Long.compare(b.getCreateTime(), a.getCreateTime()))

                // 打包成 List 返回
                .collect(Collectors.toList());
    }
    @Override
    public ContextVO getChat(String sessionId, String conversationId) {
        // 获取用户信息
        UserSession userSession = memoryRepository.getSession(sessionId);
        // 获取所有会话
        ConcurrentHashMap<String, DemoContext> conversationMap = userSession.getConversationMap();
        // 从会话中找出目标对话并返回
        DemoContext demoContext = conversationMap.get(conversationId);

        //如果为空则报错
        if (demoContext == null) {
            throw new RuntimeException("不存在该对话");
        }

        return new ContextVO(demoContext.getHistory());
    }
}
