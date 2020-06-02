package com.aaa.six.controller;

import com.aaa.six.service.IQYService;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/29 23:42
 * @Description
 */
public class UserController extends BaseController {

    @Autowired
    private IQYService iqyService;

    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user){
        //判断是否添加成功
        //如果成功了就返会系统code 系统信息
        if(iqyService.addUser(user)){
            return loginSuccess();
        }
        //如果失败了就返会系统失败code 系统信息
        else{
            return loginFailed();
        }
    }
}
