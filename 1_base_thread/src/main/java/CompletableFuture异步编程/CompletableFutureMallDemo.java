package CompletableFuture异步编程;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhul
 * @create 2022/10/20 16:14
 * <p>
 * <p>
 * 案例说明：电商比价需求，模拟以下情况
 * 1、需求：
 * 1.1: 同一款产品，同时搜索出同款产品在各大电商平台的售价
 * 2.2：同一款产品，同事搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * <p>
 * 2、输出：出来结果希望是同款产品在不同地方的价格清单，返回一格List<String>
 * 《mysql》 in jd price is 88.05
 * 《mysql》 in dangdang price is 86.11
 * 《mysql》 in taobao price is 90.43
 * <p>
 * 3、技术要求
 * 3.1 函数式编程
 * 3.2 链式编程
 * 3.3 Stream流式编程
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("pdd"),
            new NetMall("tianmao"),
            new NetMall("taobao")
    );

    /**
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }


    /**
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        final List<String> list1 = getPriceByCompletableFuture(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("-----costTime:" + (endTime - startTime) + "毫秒");


    }

}


class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

    }


}
