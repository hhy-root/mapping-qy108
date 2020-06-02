package com.aaa.six.controller;

import com.aaa.six.model.Principal;
import com.aaa.six.model.User;
import com.aaa.six.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-16 11:16
 * @description:
 *      单位负责人管理
 **/
@RestController
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

    /**
     * @author lwq
     * @description
     *    查询单位负责人信息
     * @param: [userId]
     * @date 2020/6/1
     * @return java.util.List<com.aaa.six.model.Principal>
     * @throws
     **/
    @PostMapping("/selectPrincipalInfo")
    public List<Principal> selectPrincipalInfo(@RequestParam("userId") Long userId){
        List<Principal> principals = principalService.selectPrincipalInfo(userId);
        if (principals.size()>0){
            return principals;
        }
        return null;
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
    public Boolean addPrincipal(@RequestBody Principal principal){
        Boolean aBoolean = principalService.addPrincipal(principal);
        if (aBoolean){
            return true;
        }
        return false;
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
    public Boolean deletePrincipal(@RequestBody Principal principal){
        boolean b = principalService.deletePrincipal(principal);
        if (b){
            return true;
        }
        return false;
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
    public Boolean updatePrincipal(@RequestBody Principal principal){
        return principalService.updatePrincipal(principal);
    }

}
