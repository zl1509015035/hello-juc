package com.zhul.register.server.model;

import lombok.Data;

/**
 * 注册响应
 * @author juanwang
 * @create 2022/3/19 16:53
 */
@Data
public class RegisterResponse {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";



    /*
        注册响应状态
     */
    private String status;

    /*
        注册响应码
     */
    private Integer code;

    /*
        注册响应的消息
     */
    private String message;

}
