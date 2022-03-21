package com.zhul.register.server.model;

import lombok.Data;

/**
 * 代表一个服务实例
 * 包含服务实例所有信息
 *
 * @author juanwang
 * @create 2022/3/20 20:55
 */
@Data
public class ServiceInstance {


    /**
     * 判断一个服务实例不再存活的周期
     */
    private static final Long NOT_ALIVE_PERIOD = 90 * 1000L;


    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 主机名
     */
    private String hostName;

    /**
     * 端口号
     */
    private int port;

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    /**
     * 契约
     */
    private Lease lease;

    public ServiceInstance() {
        this.lease = new Lease();
    }

    public void renew() {
        this.lease.renew();
    }

    public Boolean isAlive() {
        return this.lease.isAlive();
    }


    /**
     * 契约
     * 维护一个服务实例和当前注册中心之间的联系
     * 包括心跳时间，创建时间等
     *
     * @author juanwang
     * @create 2022/3/20 20:55
     */
    private class Lease {


        /*
         *  最近一次心跳的时间
         */
        private Long latestHeartBeatTime = System.currentTimeMillis();

        public void renew() {
            this.latestHeartBeatTime = System.currentTimeMillis();
            System.out.println("服务实例【" + serviceInstanceId + "】,进行续约:" + latestHeartBeatTime);
        }

        public Boolean isAlive() {
            Long currentTime = System.currentTimeMillis();
            if (currentTime - latestHeartBeatTime > NOT_ALIVE_PERIOD) {
                System.out.println("服务实例【"+serviceInstanceId+"】，不在存活");
                return false;
            }
            System.out.println("服务实例【"+serviceInstanceId+"】，保持存活");
            return true;
        }
    }


}
