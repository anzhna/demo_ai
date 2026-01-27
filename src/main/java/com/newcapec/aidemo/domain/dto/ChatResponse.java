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
public class ChatResponse<T> {

    private Integer code; // 200, 500
    private String msg;   // "success"
    private T data;

    // 成功时的构造工厂
    public static <T> ChatResponse<T> success(T data) {
        ChatResponse<T> response = new ChatResponse<>();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    //错误时的构造工厂
    public static <T> ChatResponse<T> error(T data) {
        ChatResponse<T> response = new ChatResponse<>();
        response.setCode(500);
        response.setMsg("error");
        response.setData(data);
        return response;
    }
}
