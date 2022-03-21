package com.zhul.register.server;

import com.zhul.register.server.model.Registry;
import com.zhul.register.server.model.ServiceInstance;

import java.util.Map;

/**
 * 微服务存活状态监控组件
 *
 * @author juanwang
 * @create 2022/3/21 21:56
 */
public class ServiceAliveMonitor {

    /**
     * 检查服务实例是否存活的间隙
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60*1000L;

    private Daemon daemon = new Daemon();

    /**
     * 启动后台线程
     */
    public void start() {
        daemon.start();
    }

    /**
     * 负责监控微服务存活状态的后台线程
     */
    private class Daemon extends Thread {

        private Registry registry = Registry.getInstance();

        @Override
        public void run() {

            Map<String, Map<String, ServiceInstance>> registryMap = null;

            while (true) {

                try {
                     registryMap = registry.getRegistry();

                     for(String serviceName : registryMap.keySet()){
                         Map<String,ServiceInstance> serviceInstanceMap = registryMap.get(serviceName);

                         for (ServiceInstance serviceInstance:serviceInstanceMap.values()){

                             /**
                              * 上一次发送心跳已经超过90秒，认为此服务死了
                              * 从注册表中摘除这个服务实例
                              */
                             if(!serviceInstance.isAlive()){
                                 registry.remove(serviceName,serviceInstance.getServiceInstanceId());
                             }
                         }
                     }



                    Thread.sleep(60 * 1000);
                } catch (Exception e) {

                }
            }

        }
    }

}
