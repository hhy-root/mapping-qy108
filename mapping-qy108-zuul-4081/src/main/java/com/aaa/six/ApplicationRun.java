package com.aaa.six;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/6/4 7:33
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.aaa.six"})
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }
}
