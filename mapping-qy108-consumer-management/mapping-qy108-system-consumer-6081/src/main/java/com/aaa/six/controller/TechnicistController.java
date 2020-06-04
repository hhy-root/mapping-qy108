package com.aaa.six.controller;


import com.aaa.six.base.BaseController;
import com.aaa.six.base.ResultData;
import com.aaa.six.model.TechResult;
import com.aaa.six.model.Technicist;
import com.aaa.six.service.IQYTechService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-06-01 23:09
 * @description:
 *      技术员信息管理
 **/
@RestController
@Api(value = "技术员信息管理", tags = "技术员信息管理")
public class TechnicistController extends BaseController {

    @Autowired
    private IQYTechService iqyTechService;

    /**
     * @author lwq
     * @description
     *    根据userId查询技术员信息
     * @param: [userId]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/selectTechnicistInfo")
    public ResultData selectTechnicistInfo(@RequestParam("userId") Long userId) {
        List<Technicist> technicists = iqyTechService.selectTechnicistInfo(userId);
        if (technicists.size()>0) {
            return selectSuccess(technicists);
        }
        return selectFailed();
    }

    /**
     * @author lwq
     * @description
     *    新增技术员
     * @param: [technicist]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/addTechnicist")
    public ResultData addTechnicist(Technicist technicist){
        Boolean aBoolean = iqyTechService.addTechnicist(technicist);
        if (aBoolean){
            return super.insertSuccess();
        }
        return super.insertFailed();
    }
    /**
     * @author lwq
     * @description
     *    删除技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/deleteTechnicist")
    public ResultData deleteTechnicist(Technicist technicist){
        Boolean aBoolean = iqyTechService.deleteTechnicist(technicist);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @author lwq
     * @description
     *    修改技术员信息
     * @param: [technicist]
     * @date 2020/6/1
     * @return com.aaa.six.base.ResultData
     * @throws
     **/
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(Technicist technicist){
        Boolean aBoolean = iqyTechService.updateTechnicist(technicist);
        if (aBoolean){
            return super.updateSuccess();

        }else {
            return super.updateFailed();
        }
    }

    /**
     * @Author: ly
     * @description:
     *
     *       数据统计  获取单位技术人员 和项目数量
     * @date: 2020/6/3
     * @param userId
     * @return: com.aaa.six.model.TechResult
     *
     */
    @PostMapping("/selectTechTypeNum")
    public ResultData selectTechTypeNum(@RequestParam("userId") Integer userId){
        TechResult techResult = iqyTechService.selectTechTypeNum(userId);
        if (null!=techResult && !"".equals(techResult)){
            return selectSuccess(techResult);
        }
        return null;
    }
}
