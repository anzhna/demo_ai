package com.newcapec.aidemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    // 角色: user(用户) / assistant(AI)
    private String role;

    // 消息类型: text(普通文本) / resource(资源卡片) / prompt_confirm(待确认)
    private String type;

    // 内容 (如果是 resource 类型，这里可能是 "视频生成成功")0
    private String content;

    // 关键字段：资源ID
    private String resourceId;

    // 时间戳
    private long time;
}