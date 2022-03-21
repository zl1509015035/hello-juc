package com.zhul.register.client;

import com.zhul.register.client.heartbeat.HeartBeatRequest;
import com.zhul.register.client.heartbeat.HeartBeatResponse;
import com.zhul.register.client.pojo.RegisterRequest;
import com.zhul.register.client.pojo.RegisterResponse;
import com.zhul.register.client.util.HttpSender;

/**
 * 负责向register-server发送注册的请求
 *
 * @author juanwang
 * @create 2022/3/19 16:49
 */
public class RegisterClientWorker extends Thread {

    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "192.168.31.207";
    public static final String HOST_NAME = "inventory01";
    public static final int PORT = 9000;

    /*
        http通信组件
     */
    private HttpSender httpSender;

    /*
        是否完成服务注册
     */
    private Boolean finishedRegister;

    /*
        服务实例id
     */
    private String serviceInstanceId;


    public RegisterClientWorker(String serviceInstanceId) {
        this.httpSender = new HttpSender();
        this.finishedRegister = false;
        this.serviceInstanceId = serviceInstanceId;
    }

    @Override
    public void run() {
        //获取当前机器信息、ip地址、hostname以及服务监听的端口号
        //可从配置文件中获取到
        if (!finishedRegister) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setServiceName(SERVICE_NAME);
            registerRequest.setIp(IP);
            registerRequest.setHostName(HOST_NAME);
            registerRequest.setPort(PORT);
            registerRequest.setServiceInstanceId(serviceInstanceId);

            RegisterResponse response = httpSender.register(registerRequest);

            System.out.println("服务注册的结果是:" + response.getStatus() + "......");

            //注册成功
            if (RegisterResponse.SUCCESS.equals(response.getStatus())) {
                this.finishedRegister = true;
            } else {
                return;
            }
        }

        //注册成功，进入while true 死循环
        if (finishedRegister) {
            HeartBeatRequest heartBeatRequest = new HeartBeatRequest();
            heartBeatRequest.setServiceInstanceId(serviceInstanceId);
            heartBeatRequest.setServiceInstanceName(SERVICE_NAME);
            HeartBeatResponse heartBeatResponse = null;

            while (true) {
                try {
                    heartBeatResponse = httpSender.heartbeat(heartBeatRequest);
                    System.out.println("心跳的结果为:" + heartBeatResponse.getStatus() + "......");
                    Thread.sleep(30 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
