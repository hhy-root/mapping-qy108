package com.aaa.six.service;

import com.aaa.six.base.ResultData;
import com.aaa.six.model.*;
import com.aaa.six.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/16 8:44
 * @Description
 *      fallbackFactory:就是来实现熔断的，在实际开发中，开发阶段不能去开启熔断
 *      因为一旦开启了熔断，整个异常都不会再抛出，不方便调bug
 *
 *      实际开发必须注意的东西:
 *          无论是springcloud1.x还是2.x版本
 *          一旦使用feign来进行传递参数的时候，必须要注意两个点:
 *              1.如果是简单类型(8种基本类型，String)--->必须使用注解@RequestParam
 *                  基本类型可以传多个，也就是说一个方法的参数中可以使用多@RequestParam
 *
 *              2.如果传递包装类型(List, Map, Vo, Po)，只能传递一个，而且必须要使用@RequestBody注解
 *
 *         也就是说最终把这些参数值传递到provider项目的controller中，在这provider项目的controller中也必须使用
 *         相同的注解，而且provider和api的方法必须要一模一样(copy是最方便的)
 *
 */
@FeignClient(value = "system-interface")
public interface IQYService {

    /**
     * @return TokenVo
     * @throws
     * @author hhy
     * @description 执行登陆操作
     * @param: [user]
     * @date 2020/5/16 9:08
     */
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /**
     * @return com.aaa.six.base.ResultData
     * @throws
     * @author lwq
     * @description 添加登录日志信息
     * @param: [map]
     * @date 2020/5/28
     **/
    @PostMapping("/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map);

}