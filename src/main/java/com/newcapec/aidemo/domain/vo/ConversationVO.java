package com.newcapec.aidemo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationVO {
    private String conversationId; // 会话ID
    private String demoId;         // 属于哪个功能
    private String title;          // 标题 (如: "生成一个红色小球")
    private long createTime;       // 时间
}
