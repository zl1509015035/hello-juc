package com.zhul.register.server.model;

import lombok.Data;

/**
 * 注册请求
 * @author juanwang
 * @create 2022/3/19 16:51
 */
@Data
public class RegisterRequest {

    /*
        服务名称
     */
    private String serviceName;

    /*
        服务所在机器的ip地址
     */
    private String ip;

    /*
        服务所在机器的主机名
     */
    private String hostName;

    /*
        端口号
     */
    private int port;

    /*
    服务实例id
     */
    private String serviceInstanceId;
}
