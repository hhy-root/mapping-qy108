package com.aaa.six.config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/31 9:50
 * @Description
 */
public class FeignMultipartConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    /**
     * @author hhy
     * @description
     *      这个注解其实是spring公司提供所修改架构的注解
     *      ConditionalOnBean:当某一个bean存在的时候，被这个注解所标识的bean就会被加载
     *          假设:
     *              在spring的源码中，里面有一个bean
     *                  @Bean
     *                  public Object dataSource() {
     *                      // TODO xxx
     *                  }
     *
     *              于是某一个程序员觉得spring的数据源写的不好，自己想写一个，于是就写了一个
     *              @ConditionalOnBean(覆盖spring源码)
     *              @ConditionalOnMissingBean(spring源码没有的方法生效)
     *              @Bean
     *              public Object dataSource() {
     *
     *              }
     * @param: []
     * @date 2020/5/31 9:52
     * @return feign.codec.Encoder
     * @throws
     */
    @Bean
    @ConditionalOnBean

    public feign.codec.Encoder springFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

}
