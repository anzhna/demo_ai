package com.newcapec.aidemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private int code;       // 1 成功 0 失败
    private String msg;     // 错误信息
    private ResponseData data; // 具体的业务数据

    @Data
    @Builder
    public static class ResponseData {
        // 响应类型: text, prompt_confirm, resource
        private String type;

        // 展示内容
        private String content;

        // 资源ID (下载用)
        private String resourceId;

        // 当前状态: IDLE, WAITING_CONFIRM, FINISHED
        private String status;

        // 扩展元数据 (存放缩略图URL、调试信息等)
        private Map<String, Object> meta;
    }

    // 快速构建成功响应的静态方法
    public static ChatResponse success(ResponseData data) {
        return ChatResponse.builder().code(0).msg("success").data(data).build();
    }

    // 快速构建错误响应
    public static ChatResponse error(int code, String msg) {
        return ChatResponse.builder().code(code).msg(msg).build();
    }
}
