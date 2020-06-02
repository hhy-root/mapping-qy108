package com.aaa.six.controller;

import com.aaa.six.base.BaseService;
import com.aaa.six.base.CommonController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.Audit;
import com.aaa.six.model.LoginLog;
import com.aaa.six.service.AuditService;
import com.aaa.six.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/28 19:08
 * @Description
 */
@RestController
public class LogController extends CommonController<LoginLog> {

    @Autowired
    private LoginLogService loginLogService;


    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }

    /**
     * @author hhy
     * @description
     *    实现登录日志的保存
     * @param: [map]
     * @date 2020/5/28 19:13
     * @return com.aaa.six.base.ResultData
     * @throws 
     */
    @PostMapping("/addLoginLog")
    public ResultData addLoginLog(@RequestBody Map map){
        return super.add(map);
    }


}
