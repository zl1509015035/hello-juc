package com.zhul.register.server.model;

import lombok.Data;

/**
 * @author juanwang
 * @create 2022/3/19 18:05
 */
@Data
public class HeartBeatRequest {

    /*
        服务实例id
     */
    private String serviceInstanceId;

    /*
        服务名称
    */
    private String serviceName;
}
