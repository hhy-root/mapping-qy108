package com.aaa.six.controller;

import com.aaa.six.base.BaseService;
import com.aaa.six.base.CommonController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.LoginLog;
import com.aaa.six.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-28 10:48
 * @description:
 **/
@RestController
public class LogController extends CommonController<LoginLog> {
    
    @Autowired
    private LoginLogService loginLogService;
    
    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }
    
    /**
     * @author lwq 
     * @description
     *    实现登录日志保存
     * @param: [map]
     * @date 2020/5/28
     * @return com.aaa.six.base.ResultData
     * @throws 
     **/
    @PostMapping("/addLoginLog")
    public ResultData addLoginLog(@RequestBody Map map) {
        return super.add(map);
    }
}
