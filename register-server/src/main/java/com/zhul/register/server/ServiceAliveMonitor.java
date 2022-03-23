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
    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    /**
     * 负责监控微服务存活状态的后台线程
     */
    private Daemon daemon;

    public ServiceAliveMonitor() {
        /**
         * enumerate():赋值线程组里的线程
         * activeCount():获取线程组里活跃的线程
         * getName()、getParent()、list()等等
         * interrupt():打断所有的线程
         * destroy():一次性destroy所有的线程
         */
//        ThreadGroup demonThreadGroup = new ThreadGroup("daemon");

//        System.out.println("daemon线程组的父线程组是:"+demonThreadGroup.getParent());

        this.daemon = new Daemon();
        //只要设置了这个标志位，就代表这个线程是一个daemon线程，后台线程
        //非daemon线程，我们一般叫做工作线程
        //如果工作线程(main线程)结束，daemon线程是不会阻止jvm进程退出的
        //daemon线程会跟着jvm进程一起退出
        daemon.setDaemon(true);
        daemon.setName("ServiceAliveMonitor");
    }


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

            System.out.println(Thread.currentThread().getName() + "线程的线程组是：" + Thread.currentThread().getThreadGroup());


            while (true) {

                try {
                    registryMap = registry.getRegistry();

                    for (String serviceName : registryMap.keySet()) {
                        Map<String, ServiceInstance> serviceInstanceMap = registryMap.get(serviceName);

                        for (ServiceInstance serviceInstance : serviceInstanceMap.values()) {

                            /**
                             * 上一次发送心跳已经超过90秒，认为此服务死了
                             * 从注册表中摘除这个服务实例
                             */
                            if (!serviceInstance.isAlive()) {
                                registry.remove(serviceName, serviceInstance.getServiceInstanceId());
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
