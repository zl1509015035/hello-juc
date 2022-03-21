package com.zhul.register.server.model;

import lombok.Data;

/**
 * @author juanwang
 * @create 2022/3/19 18:17
 */
@Data
public class HeartBeatResponse {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    /*
        心跳响应状态
     */
    private String status;

}
