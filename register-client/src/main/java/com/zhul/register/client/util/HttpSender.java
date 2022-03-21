package com.zhul.register.client.util;

import com.zhul.register.client.pojo.RegisterRequest;
import com.zhul.register.client.pojo.RegisterResponse;
import com.zhul.register.client.heartbeat.HeartBeatRequest;
import com.zhul.register.client.heartbeat.HeartBeatResponse;

/**
 * 负责发送各种http请求的组件
 * @author juanwang
 * @create 2022/3/19 16:50
 */
public class HttpSender {

    /**
     * 发送注册的请求
     * @param request 注册请求
     * @return 注册响应
     */
    public RegisterResponse register(RegisterRequest request){
        //构造请求，放入服务实例的信息
        System.out.println("服务实例【"+request+"】,发送请求进行注册......");

        //收到register-server响应之后,封装响应
        RegisterResponse response = new RegisterResponse();
        response.setStatus(RegisterResponse.SUCCESS);

        return response;
    }

    /**
     * 发送注册的请求
     * @param request 注册请求
     * @return 注册响应
     */
    public HeartBeatResponse heartbeat(HeartBeatRequest request){
        //构造请求，放入服务实例的信息
        System.out.println("服务实例【"+request+"】,发送心跳请求......");

        //收到register-server响应之后,封装响应
        HeartBeatResponse response = new HeartBeatResponse();
        response.setStatus(RegisterResponse.SUCCESS);

        return response;
    }


}
