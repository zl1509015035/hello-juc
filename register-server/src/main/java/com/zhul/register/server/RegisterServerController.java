package com.zhul.register.server;

import com.zhul.register.server.model.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个controller负责接受register-client发送过来的请求
 *
 * @author juanwang
 * @create 2022/3/20 20:48
 */
@RestController
public class RegisterServerController {

    private Registry registry = Registry.getInstance();

    /**
     * 服务注册
     *
     * @param registerRequest 注册请求
     * @return 注册响应
     */
    public RegisterResponse register(RegisterRequest registerRequest) {

        RegisterResponse registerResponse = new RegisterResponse();


        try {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setHostName(registerRequest.getHostName());
            serviceInstance.setIp(registerRequest.getIp());
            serviceInstance.setPort(registerRequest.getPort());
            serviceInstance.setServiceInstanceId(registerRequest.getServiceInstanceId());
            serviceInstance.setServiceName(registerRequest.getServiceName());

            registry.register(serviceInstance);

            registerResponse.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            registerResponse.setStatus(RegisterResponse.FAILURE);
        }


        return registerResponse;
    }

    /**
     * 发送心跳
     *
     * @param heartBeatRequest 心跳请求
     * @return 心跳响应
     */
    public HeartBeatResponse heartbeat(HeartBeatRequest heartBeatRequest) {

        HeartBeatResponse heartBeatResponse = new HeartBeatResponse();

        try {
            ServiceInstance serviceInstance = registry.getServiceInstance(
                    heartBeatRequest.getServiceName(), heartBeatRequest.getServiceInstanceId());
            serviceInstance.renew();

            heartBeatResponse.setStatus(HeartBeatResponse.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            heartBeatResponse.setStatus(HeartBeatResponse.FAILURE);
        }

        return heartBeatResponse;
    }

}
