package com.aaa.six.controller;

import com.aaa.six.IQYService;
import com.aaa.six.annotation.LoginLogAnnotation;
import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.User;
import com.aaa.six.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/16 8:09
 * @Description
 *      用户登录
 */
@RestController
@Api(value = "登录信息", tags = "用户登录接口")
public class LoginController extends BaseController {

    @Autowired
    private IQYService qyService;

    /**
     * @author hhy
     * @description
     *    执行登录操作
     * @param: [user]
     * @date 2020/5/16 8:23
     * @return com.aaa.six.base.ResultData
     * @throws 
     */
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录功能", notes = "用户执行登录功能")
    @LoginLogAnnotation(operationType = "登录操作",operationName = "管理员登录")
    public ResultData doLogin(User user){
        TokenVo tokenVo = qyService.doLogin(user);
        if(tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        }
        return super.loginFailed();
    }
}


