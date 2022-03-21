package com.zhul.register.server.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表组件
 *
 * @author juanwang
 * @create 2022/3/20 20:54
 */
public class Registry {

    /*
        单例
     */
    private static Registry instance = new Registry();

    public Registry() {
    }

    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    public void register(ServiceInstance serviceInstance) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServiceName());
        if (serviceInstanceMap == null) {
            serviceInstanceMap = new HashMap<String, ServiceInstance>();
            registry.put(serviceInstance.getServiceName(), serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);

        System.out.println("服务实例【" + serviceInstance + "】,完成注册......");
        System.out.println("注册表:" + registry);
    }

    /**
     * 获取服务实例
     *
     * @param serviceName       服务名称
     * @param serviceInstanceId 服务实例id
     * @return 服务实例
     */
    public ServiceInstance getServiceInstance(String serviceName, String serviceInstanceId) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        return serviceInstanceMap.get(serviceInstanceId);
    }

    /**
     * 获取整个注册表
     *
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    public void remove(String serviceName, String serviceInstanceId) {
        System.out.println("服务实例【"+serviceInstanceId+"】，从注册表中进行摘除");
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        serviceInstanceMap.remove(serviceInstanceId);
    }

    public static Registry getInstance() {
        return instance;
    }
}
