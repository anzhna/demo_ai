package com.newcapec.aidemo.service;

import com.newcapec.aidemo.domain.entity.DemoContext;
import com.newcapec.aidemo.domain.vo.ContextVO;
import com.newcapec.aidemo.domain.vo.ConversationVO;

import java.util.List;

public interface HistoryService {

    /**
     * 获取demo历史
     * @param sessionId
     * @param demoId
     * @return
     */
    List<ConversationVO> getHistory(String sessionId, String demoId);

    /**
     * 获取对话
     * @param sessionId
     * @return
     */
    ContextVO getChat(String sessionId, String conversationId);
}
