package com.newcapec.aidemo.domain.vo;

import com.newcapec.aidemo.domain.entity.ChatMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ContextVO {
    // 历史消息列表
    private List<ChatMessage> history = new ArrayList<>();

    public ContextVO(List<ChatMessage> history) {
        this.history = history;
    }
}
