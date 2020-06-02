package com.aaa.six.controller;

import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.service.IQYPrincipalService;

import com.aaa.six.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 21:24
 * @description:
 *      单位负责人管理
 **/
@RestController
public class PrincipalController extends BaseController {

    @Autowired
    private IQYPrincipalService iqyPrincipalService;

    /**
     * @author lwq
     * @description
     *    获取单位负责人信息
     * @param: [userId]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/selectPrincipalInfo")
    public ResultData selectPrincipalInfo(@RequestParam("userId") Long userId) {
        List<Principal> principals = iqyPrincipalService.selectPrincipalInfo(userId);
        if (principals.size()>0) {
            return selectSuccess(principals);
        }
        return selectFailed();
    }

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
    public ResultData addPrincipal(Principal principal){
        Boolean aBoolean = iqyPrincipalService.addPrincipal(principal);
        if (aBoolean){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
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
    public ResultData deletePrincipal(Principal principal){
        Boolean aBoolean = iqyPrincipalService.deletePrincipal(principal);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

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
    public ResultData updatePrincipal(Principal principal){
        Boolean aBoolean = iqyPrincipalService.updatePrincipal(principal);
        if (aBoolean){
            return super.updateSuccess();

        }else {
            return super.updateFailed();
        }
    }
}