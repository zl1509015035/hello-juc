package com.zhul.register.client;

import java.util.UUID;

/**
 * 在服务上被创建和启动，负责跟register-server进行通信
 *
 * @author juanwang
 * @create 2022/3/19 16:47
 */
public class RegisterClient {

    /*
        服务实例id
     */
    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
    }

    public void start() {
        //1、开启线程向register-server发送请求，注册此服务
        //2、注册成功后，开启另外一个线程去发送心跳

        // 注册完成之后，进入while true死循环
        //每隔30秒发送一个请求进行心跳

        new RegisterClientWorker(serviceInstanceId).start();
    }
}
