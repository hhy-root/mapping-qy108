package com.aaa.six.service;/*
 *@Company：
 *@Author：何康
 *@Date：2020/5/26 15:02
 *@Description:
 */

import com.aaa.six.model.Principal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 21:24
 * @description:
 *      单位负责人管理
 **/
@FeignClient(value = "prin-interface")
public interface IQYPrincipalService {
    /**
     * @author lwq
     * @description
     *    获取单位负责人信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Principal>
     * @throws
     **/
    @PostMapping("/selectPrincipalInfo")
    List<Principal> selectPrincipalInfo(@RequestParam("userId") Long userId);


    /**
     * @author lwq
     * @description
     *    新增单位负责人
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/addPrincipal")
    Boolean addPrincipal(@RequestBody Principal principal);
    /**
     * @author lwq
     * @description
     *    删除单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/deletePrincipal")
    Boolean deletePrincipal(@RequestBody Principal principal);

    /**
     * @author lwq
     * @description
     *    修改单位负责人信息
     * @param: [principal]
     * @date 2020/6/1
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping("/updatePrincipal")
    Boolean updatePrincipal(@RequestBody Principal principal);

}
