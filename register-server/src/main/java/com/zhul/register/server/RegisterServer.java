package com.zhul.register.server;

import com.zhul.register.server.model.HeartBeatRequest;
import com.zhul.register.server.model.RegisterRequest;

import java.util.UUID;

/**
 * @author juanwang
 * @create 2022/3/20 21:30
 */
public class RegisterServer {

    public static void main(String[] args) throws Exception {

        RegisterServerController controller = new RegisterServerController();

        String serviceInstanceId = UUID.randomUUID().toString().replace("-", "");

        // 模拟发起了一个服务注册的请求
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setHostName("inventory-service-01");
        registerRequest.setIp("192.168.31.208");
        registerRequest.setPort(9000);
        registerRequest.setServiceInstanceId(serviceInstanceId);
        registerRequest.setServiceName("inventory-service");

        controller.register(registerRequest);

        //模拟进行一次心跳，完成续约
        HeartBeatRequest heartBeatRequest = new HeartBeatRequest();
        heartBeatRequest.setServiceName("inventory-service");
        heartBeatRequest.setServiceInstanceId(serviceInstanceId);

        controller.heartbeat(heartBeatRequest);

        //开启一个后台线程，检查微服务后台状态
        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();

        while (true) {
            Thread.sleep(30 * 1000);
        }

    }
}
